package com.thetradingpit.msregister.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientRequest {
    private UUID clientId;
    private String referralCode;
    private String landingPage;
    private String userAgent;
    private String ip;
}
