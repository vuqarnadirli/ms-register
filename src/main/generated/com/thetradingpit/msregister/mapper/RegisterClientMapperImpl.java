package com.thetradingpit.msregister.mapper;

import com.thetradingpit.msregister.model.dto.request.RegisterClientRequest;
import com.thetradingpit.msregister.model.entity.AffiliateClientMap;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T21:58:23+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Amazon.com Inc.)"
)
@Component
public class RegisterClientMapperImpl implements RegisterClientMapper {

    @Override
    public AffiliateClientMap toAffiliateClientMap(RegisterClientRequest request, UUID clickId) {
        if ( request == null && clickId == null ) {
            return null;
        }

        AffiliateClientMap affiliateClientMap = new AffiliateClientMap();

        if ( request != null ) {
            affiliateClientMap.setClientIp( request.getIp() );
            affiliateClientMap.setReferralCode( request.getReferralCode() );
            affiliateClientMap.setUserAgent( request.getUserAgent() );
        }
        if ( clickId != null ) {
            affiliateClientMap.setClickId( clickId );
        }

        return affiliateClientMap;
    }
}
