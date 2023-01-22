package com.thetradingpit.msregister.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AffiliateClientRegistrationResponse {
    private UUID clickId;

    public static AffiliateClientRegistrationResponse of(UUID id) {
        return new AffiliateClientRegistrationResponse(id);
    }
}
