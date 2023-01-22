package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.dto.request.RegisterConversionRequest;
import com.thetradingpit.msregister.model.entity.FailedCalls;
import feign.FeignException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FailedCallsMapper {

    @Mapping(target = "clientId", source = "request.clientId")
    @Mapping(target = "reason", source = "feignException.message")
    @Mapping(target = "processed", constant = "false")
    FailedCalls toFailedCall(RegisterClientRequest request, FeignException feignException);

    @Mapping(target = "clientId", source = "request.clientId")
    @Mapping(target = "reason", source = "feignException.message")
    @Mapping(target = "processed", constant = "false")
    FailedCalls toFailedCall(RegisterConversionRequest request, FeignException feignException);
}
