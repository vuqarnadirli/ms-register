package com.thetradingpit.msregister.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientConversionRequest {
    private UUID clickId;
    private UUID externalId;
    private BigDecimal amount;
    private String currency;
    private UUID customerId;
    private String userAgent;
    private String ip;
}
