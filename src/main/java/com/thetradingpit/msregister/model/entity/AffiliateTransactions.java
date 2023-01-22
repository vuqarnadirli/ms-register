package com.thetradingpit.msregister.model.entity;

import com.thetradingpit.msregister.model.TransactionTypeEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "AFFILIATE_TRANSACTIONS")
@Data
public class AffiliateTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long transactionId;

    @Column(name = "conversion_id")
    private Long conversionId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Column(name = "conversion_amount")
    private BigDecimal conversionAmount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;

    @Column(name = "creation_date")
    @CreationTimestamp
    private Timestamp creationDate;

}
