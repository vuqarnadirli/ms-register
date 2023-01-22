package com.thetradingpit.msregister.service;

import com.thetradingpit.msregister.client.api.AffiliateClientRegistrationClient;
import com.thetradingpit.msregister.client.api.AffiliateConversionRegistrationClient;
import com.thetradingpit.msregister.client.model.Affiliate;
import com.thetradingpit.msregister.client.model.AffiliateClientRegistrationResponse;
import com.thetradingpit.msregister.client.model.AffiliateConversionRegistrationResponse;
import com.thetradingpit.msregister.client.model.ClientConversionRequest;
import com.thetradingpit.msregister.client.model.Program;
import com.thetradingpit.msregister.mapper.FailedCallsMapper;
import com.thetradingpit.msregister.mapper.RegisterClientMapper;
import com.thetradingpit.msregister.mapper.RegisterConversionMapper;
import com.thetradingpit.msregister.model.RequestTypeEnum;
import com.thetradingpit.msregister.model.TransactionTypeEnum;
import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.dto.response.RegisterClientResponse;
import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import com.thetradingpit.msregister.model.entity.AffiliateTransactions;
import com.thetradingpit.msregister.model.entity.FailedCalls;
import com.thetradingpit.msregister.repository.AffiliateClientRepository;
import com.thetradingpit.msregister.repository.AffiliateTransactionRepository;
import com.thetradingpit.msregister.repository.FailedCallsRepository;
import com.thetradingpit.msregister.util.JsonProviderUtil;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {
    @Mock
    private AffiliateClientRegistrationClient client;
    @Mock
    private AffiliateClientRepository affiliateClientRepository;
    @Mock
    private RegisterConversionMapper conversionMapper;
    @Mock
    private AffiliateConversionRegistrationClient conversionClient;
    @Mock
    private AffiliateTransactionRepository affiliateTransactionRepository;
    @Mock
    private FailedCallsRepository failedCallsRepository;
    @Mock
    private RegisterClientMapper registerClientMapper;
    @Mock
    private FailedCallsMapper failedCallsMapper;
    @Mock
    private JsonProviderUtil jsonProviderUtil;

    @InjectMocks
    private RegisterService registerService;

    @Test
    void testRegisterClient_success() {
        UUID clickId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        String referralCode = "referralCode";
        String landingPage = "landingPage";
        String userAgent = "userAgent";
        String ip = "ip";
        RegisterClientRequest request = new RegisterClientRequest(clientId, referralCode, landingPage, userAgent, ip);
        AffiliateClientMap affiliateClientMap = new AffiliateClientMap(clientId, referralCode, clickId, ip, userAgent);
        when(client.register(request)).thenReturn(AffiliateClientRegistrationResponse.of(clickId));
        when(registerClientMapper.toAffiliateClientMap(request, clickId)).thenReturn(affiliateClientMap);
        when(affiliateClientRepository.save(affiliateClientMap)).thenReturn(affiliateClientMap);

        RegisterClientResponse response = registerService.registerClient(request);

        assertEquals(clickId, response.getClickId());
        verify(affiliateClientRepository, times(1)).save(affiliateClientMap);
        verify(jsonProviderUtil, times(0)).toJson(request);
        verify(failedCallsRepository, times(0)).save(any());
    }

    @Test
    void testRegisterClient_failure() {
        UUID clientId = UUID.randomUUID();
        String referralCode = "referralCode";
        String landingPage = "landingPage";
        String userAgent = "userAgent";
        String ip = "ip";
        RegisterClientRequest request = new RegisterClientRequest(clientId, referralCode, landingPage, userAgent, ip);
        FeignException feignException = mock(FeignException.class);
        FailedCalls failedCalls = new FailedCalls(UUID.randomUUID(),
                clientId,
                RequestTypeEnum.CREATE_CLICK,
                "{}",
                "Error Message");

        when(client.register(request)).thenThrow(feignException);
        when(jsonProviderUtil.toJson(request)).thenReturn("{}");
        when(failedCallsMapper.toFailedCall(request, feignException)).thenReturn(failedCalls);
        when(failedCallsRepository.save(failedCalls)).thenReturn(failedCalls);

        try {
            registerService.registerClient(request);
            fail("Expected exception to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Failed to register client after 3 tries", e.getMessage());
            verify(affiliateClientRepository, times(0)).save(any());
            verify(jsonProviderUtil, times(1)).toJson(any());
            verify(failedCallsRepository, times(1)).save(any());
        }

    }


    @Test
    void testRegisterConversion_success() {
        UUID clientId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal totalPrice = new BigDecimal(100);
        TransactionTypeEnum transactionType = TransactionTypeEnum.NEW;
        AffiliateTransactions transaction = new AffiliateTransactions();
        RegisterConversionRequest request = new RegisterConversionRequest(orderId,
                totalPrice,
                clientId,
                transactionType);
        AffiliateClientMap affiliateClient = new AffiliateClientMap(clientId,
                "referralCode",
                UUID.randomUUID(),
                "ip",
                "userAgent");
        AffiliateConversionRegistrationResponse response = new AffiliateConversionRegistrationResponse(1L,
                UUID.randomUUID(),
                totalPrice,
                affiliateClient.getClickId(),
                Collections.emptyList(),
                new Program(),
                new Affiliate(),
                "createdAt",
                Collections.emptyList());
        ClientConversionRequest clientReq = new ClientConversionRequest(affiliateClient.getClickId(),
                UUID.randomUUID(),
                totalPrice,
                "USD",
                clientId,
                "userAgent",
                "ip");
        when(affiliateClientRepository.findById(clientId)).thenReturn(Optional.of(affiliateClient));
        when(conversionMapper.toClientConversionRequest(affiliateClient, request)).thenReturn(clientReq);
        when(conversionClient.register(clientReq)).thenReturn(response);
        when(conversionMapper.toAffiliateTransaction(response, request)).thenReturn(transaction);
        when(affiliateTransactionRepository.save(transaction)).thenReturn(transaction);

        registerService.registerConversion(request);

        verify(affiliateTransactionRepository).save(transaction);
        verify(jsonProviderUtil, never()).toJson(any());
        verify(failedCallsRepository, never()).save(any());
    }

    @Test
    void testRegisterConversion_failure() {
        UUID clientId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal totalPrice = new BigDecimal(100);
        TransactionTypeEnum transactionType = TransactionTypeEnum.NEW;
        FeignException feignException = mock(FeignException.class);
        RegisterConversionRequest request = new RegisterConversionRequest(orderId, totalPrice, clientId, transactionType);
        AffiliateClientMap affiliateClient = new AffiliateClientMap(clientId,
                "referralCode",
                UUID.randomUUID(),
                "ip",
                "userAgent");
        ClientConversionRequest clientReq = new ClientConversionRequest(affiliateClient.getClickId(),
                UUID.randomUUID(),
                totalPrice,
                "USD", clientId,
                "userAgent",
                "ip");

        FailedCalls failedCalls = new FailedCalls(UUID.randomUUID(),
                clientId,
                RequestTypeEnum.CREATE_CONVERSION,
                "json_payload",
                "error_message");

        when(affiliateClientRepository.findById(clientId)).thenReturn(Optional.of(affiliateClient));
        when(conversionMapper.toClientConversionRequest(affiliateClient, request)).thenReturn(clientReq);
        when(conversionClient.register(clientReq)).thenThrow(feignException);
        when(jsonProviderUtil.toJson(clientReq)).thenReturn("{}");
        when(failedCallsMapper.toFailedCall(request, feignException)).thenReturn(failedCalls);
        when(failedCallsRepository.save(failedCalls)).thenReturn(failedCalls);

        try {
            registerService.registerConversion(request);
            fail("Expected exception to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Failed to register conversion after 3 tries", e.getMessage());
            verify(affiliateTransactionRepository, times(0)).save(any());
            verify(jsonProviderUtil, times(1)).toJson(any());
            verify(failedCallsRepository, times(1)).save(any());
        }

    }
}
