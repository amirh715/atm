package com.pmt.atm.domain;

import com.pmt.atm.infra.persistence.attributeConverter.TomanAttributeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public abstract class Transaction {

    @Id
    private final String id;

    @Column(name = "amount_in_tomans")
    @Convert(converter = TomanAttributeConverter.class)
    private final Toman amount;

    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "failure_reason")
    private TransactionFailureReason failureReason;

    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    protected Transaction(
            Toman amount
    ) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
    }

    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.amount = Toman.createZero();
        this.createdAt = LocalDateTime.now();
    }

    public void markTransactionAsSucceeded() {
        status = TransactionStatus.SUCCEEDED;
        modified();
    }

    public void markTransactionAsFailed(TransactionFailureReason failureReason) {
        this.failureReason = failureReason;
        status = TransactionStatus.FAILED;
        modified();
    }

    private void modified() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Toman getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public TransactionFailureReason getFailureReason() {
        return failureReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

}
