package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.client.model.AffiliateConversionRegistrationResponse;
import com.thetradingpit.msregister.client.model.ClientConversionRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import com.thetradingpit.msregister.model.entity.AffiliateTransactions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T21:58:23+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Amazon.com Inc.)"
)
@Component
public class RegisterConversionMapperImpl implements RegisterConversionMapper {

    @Override
    public ClientConversionRequest toClientConversionRequest(AffiliateClientMap affiliateClientMap, RegisterConversionRequest registerConversionRequest) {
        if ( affiliateClientMap == null && registerConversionRequest == null ) {
            return null;
        }

        ClientConversionRequest clientConversionRequest = new ClientConversionRequest();

        if ( affiliateClientMap != null ) {
            clientConversionRequest.setClickId( affiliateClientMap.getClickId() );
            clientConversionRequest.setIp( affiliateClientMap.getClientIp() );
            clientConversionRequest.setUserAgent( affiliateClientMap.getUserAgent() );
        }
        if ( registerConversionRequest != null ) {
            clientConversionRequest.setExternalId( registerConversionRequest.getOrderId() );
            clientConversionRequest.setCustomerId( registerConversionRequest.getOrderId() );
            clientConversionRequest.setAmount( registerConversionRequest.getTotalPrice() );
        }

        return clientConversionRequest;
    }

    @Override
    public AffiliateTransactions toAffiliateTransaction(AffiliateConversionRegistrationResponse response, RegisterConversionRequest request) {
        if ( response == null && request == null ) {
            return null;
        }

        AffiliateTransactions affiliateTransactions = new AffiliateTransactions();

        if ( response != null ) {
            affiliateTransactions.setConversionId( response.getId() );
        }
        if ( request != null ) {
            affiliateTransactions.setClientId( request.getClientId() );
            affiliateTransactions.setOrderId( request.getOrderId() );
            affiliateTransactions.setOrderAmount( request.getTotalPrice() );
            affiliateTransactions.setTransactionType( request.getTransactionType() );
        }

        return affiliateTransactions;
    }
}
