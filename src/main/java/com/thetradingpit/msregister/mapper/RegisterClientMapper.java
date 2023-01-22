package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RegisterClientMapper {

    @Mapping(target = "clientIp", source = "request.ip")
    AffiliateClientMap toAffiliateClientMap(RegisterClientRequest request, UUID clickId);

}
