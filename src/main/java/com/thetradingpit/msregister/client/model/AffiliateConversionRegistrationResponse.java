package com.thetradingpit.msregister.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffiliateConversionRegistrationResponse {
    private Long id;
    private UUID externalId;
    private BigDecimal amount;
    private UUID click;
    private List<Comission> commissions;
    private Program program;
    private Affiliate affiliate;
    private String createdAt;
    private List<String> warnings;
}
