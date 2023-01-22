package com.thetradingpit.msregister.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Comission {
    private Long id;
    private BigDecimal conversionSubAmount;
    private BigDecimal amount;
    private String commissionType;
    private Boolean approved;
    private Affiliate affiliate;
    private String kind;
    private String currency;
    private String createdAt;
}
