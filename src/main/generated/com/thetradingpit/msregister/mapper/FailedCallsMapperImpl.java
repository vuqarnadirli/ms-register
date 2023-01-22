package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.entity.FailedCalls;
import feign.FeignException;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T21:58:23+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Amazon.com Inc.)"
)
@Component
public class FailedCallsMapperImpl implements FailedCallsMapper {

    @Override
    public FailedCalls toFailedCall(RegisterClientRequest request, FeignException feignException) {
        if ( request == null && feignException == null ) {
            return null;
        }

        FailedCalls failedCalls = new FailedCalls();

        if ( request != null ) {
            failedCalls.setClientId( request.getClientId() );
        }
        if ( feignException != null ) {
            failedCalls.setReason( feignException.getMessage() );
        }
        failedCalls.setProcessed( false );

        return failedCalls;
    }

    @Override
    public FailedCalls toFailedCall(RegisterConversionRequest request, FeignException feignException) {
        if ( request == null && feignException == null ) {
            return null;
        }

        FailedCalls failedCalls = new FailedCalls();

        if ( request != null ) {
            failedCalls.setClientId( request.getClientId() );
        }
        if ( feignException != null ) {
            failedCalls.setReason( feignException.getMessage() );
        }
        failedCalls.setProcessed( false );

        return failedCalls;
    }
}
