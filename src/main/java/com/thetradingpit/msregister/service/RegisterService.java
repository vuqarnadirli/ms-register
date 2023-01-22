package com.thetradingpit.msregister.service;

import com.thetradingpit.msregister.client.api.AffiliateClientRegistrationClient;
import com.thetradingpit.msregister.client.api.AffiliateConversionRegistrationClient;
import com.thetradingpit.msregister.exception.ClientNotFoundException;
import com.thetradingpit.msregister.mapper.FailedCallsMapper;
import com.thetradingpit.msregister.mapper.RegisterClientMapper;
import com.thetradingpit.msregister.mapper.RegisterConversionMapper;
import com.thetradingpit.msregister.model.RequestTypeEnum;
import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.dto.response.RegisterClientResponse;
import com.thetradingpit.msregister.model.dto.response.RegisterConversionResponse;
import com.thetradingpit.msregister.repository.AffiliateClientRepository;
import com.thetradingpit.msregister.repository.AffiliateTransactionRepository;
import com.thetradingpit.msregister.repository.FailedCallsRepository;
import com.thetradingpit.msregister.util.JsonProviderUtil;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RegisterClientMapper registerClientMapper;
    private final FailedCallsMapper failedCallsMapper;
    private final RegisterConversionMapper conversionMapper;
    private final AffiliateClientRegistrationClient client;
    private final AffiliateConversionRegistrationClient conversionClient;
    private final AffiliateClientRepository affiliateClientRepository;
    private final FailedCallsRepository failedCallsRepository;
    private final AffiliateTransactionRepository affiliateTransactionRepository;
    private final JsonProviderUtil jsonProviderUtil;

    @Value("${currency}")
    private String currency;

    public RegisterClientResponse registerClient(RegisterClientRequest request) {
        for (int i = 0; i < 3; i++) {
            try {
                var clickId = client.register(request).getClickId();
                var affiliateClientMap = registerClientMapper.toAffiliateClientMap(request, clickId);
                affiliateClientRepository.save(affiliateClientMap);
                return RegisterClientResponse.of(clickId);
            } catch (FeignException feignException) {
                if (i == 2) {
                    var failedCall = failedCallsMapper.toFailedCall(request, feignException);
                    failedCall.setPayload(jsonProviderUtil.toJson(request));
                    failedCall.setRequestType(RequestTypeEnum.CREATE_CLICK);
                    failedCallsRepository.save(failedCall);
                    throw new RuntimeException("Failed to register client after 3 tries");
                }
            }
        }

        return null;
    }

    public RegisterConversionResponse registerConversion(RegisterConversionRequest request) {
        var affiliateClient = affiliateClientRepository
                .findById(request.getClientId())
                .orElseThrow(() -> ClientNotFoundException.of("CLIENT IS NOT FOUND"));

        var clientReq = conversionMapper.toClientConversionRequest(affiliateClient, request);
        clientReq.setCurrency(currency);

        for (int i = 0; i < 3; i++) {
            try {
                var response = conversionClient.register(clientReq);
                var transaction = conversionMapper.toAffiliateTransaction(response, request);
                transaction.setCurrency(currency);
                affiliateTransactionRepository.save(transaction);
                return RegisterConversionResponse.of(transaction.getConversionId());
            } catch (FeignException feignException) {
                if (i == 2) {
                    var failedCall = failedCallsMapper.toFailedCall(request, feignException);
                    failedCall.setPayload(jsonProviderUtil.toJson(clientReq));
                    failedCall.setRequestType(RequestTypeEnum.CREATE_CONVERSION);
                    failedCallsRepository.save(failedCall);
                    throw new RuntimeException("Failed to register conversion after 3 tries");
                }
            }
        }

        return null;
    }
}
