package com.thetradingpit.msregister.model.entity;

import com.thetradingpit.msregister.model.RequestTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FAILED_CALLS")
public class FailedCalls {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "request_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestTypeEnum requestType;

    @Column(name = "payload", nullable = false)
    private String payload;

    @Column(name = "reason", nullable = false)
    private String reason;

    @CreationTimestamp
    @Column(name = "time_of_failure", nullable = false)
    private Timestamp time;

    @Column(name = "processed", nullable = false)
    private Boolean processed;

    public FailedCalls(UUID id, UUID clientId, RequestTypeEnum requestType, String payload, String reason) {
        this.id = id;
        this.clientId = clientId;
        this.requestType = requestType;
        this.payload = payload;
        this.reason = reason;
    }
}
