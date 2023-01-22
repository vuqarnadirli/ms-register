package com.thetradingpit.msregister.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AFFILIATE_CLIENT_MAP")
public class AffiliateClientMap {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "client_id", nullable = false)
    private UUID id;

    @Column(name = "referral_code", nullable = false)
    private String referralCode;

    @Column(name = "click_id", nullable = false)
    private UUID clickId;

    @Column(name = "client_ip", nullable = false)
    private String clientIp;

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    public AffiliateClientMap(UUID clientId, String referralCode, UUID clickId, String ip, String userAgent) {
        this.id = clientId;
        this.referralCode = referralCode;
        this.clickId = clickId;
        this.clientIp = ip;
        this.userAgent = userAgent;
    }
}
