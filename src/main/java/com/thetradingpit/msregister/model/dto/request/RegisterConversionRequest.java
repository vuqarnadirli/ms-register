package com.thetradingpit.msregister.model.dto.request;

import com.thetradingpit.msregister.model.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterConversionRequest {
    private UUID orderId;
    private BigDecimal totalPrice;
    private UUID clientId;
    private TransactionTypeEnum transactionType;
}