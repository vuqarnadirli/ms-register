package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.client.model.AffiliateConversionRegistrationResponse;
import com.thetradingpit.msregister.client.model.ClientConversionRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import com.thetradingpit.msregister.model.entity.AffiliateTransactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = "spring")
public interface RegisterConversionMapper {

    @Mapping(source = "affiliateClientMap.clickId", target = "clickId")
    @Mapping(source = "registerConversionRequest.orderId", target = "externalId")
    @Mapping(source = "affiliateClientMap.clientIp", target = "ip")
    @Mapping(source = "affiliateClientMap.userAgent", target = "userAgent")
    @Mapping(source = "registerConversionRequest.orderId", target = "customerId")
    @Mapping(source = "registerConversionRequest.totalPrice", target = "amount")
    ClientConversionRequest toClientConversionRequest(AffiliateClientMap affiliateClientMap,
                                                      RegisterConversionRequest registerConversionRequest);

    @Mapping(source = "response.id", target = "conversionId")
    @Mapping(source = "request.clientId", target = "clientId")
    @Mapping(source = "request.orderId", target = "orderId")
    @Mapping(source = "request.totalPrice", target = "orderAmount")
    @Mapping(source = "request.transactionType", target = "transactionType")
    AffiliateTransactions toAffiliateTransaction(AffiliateConversionRegistrationResponse response,
                                                 RegisterConversionRequest request);

}
