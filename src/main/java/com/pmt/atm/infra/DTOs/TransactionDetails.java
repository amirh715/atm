package com.pmt.atm.infra.DTOs;

import com.pmt.atm.domain.*;

import java.time.format.DateTimeFormatter;

public class TransactionDetails {

    private final String id;

    private final String type;

    private final String amountInTomans;

    private final String senderAccountNumber;

    private final String senderCustomerId;

    private final String receiverAccountNumber;

    private final String receiverCustomerId;

    private final String status;

    private final String createdAt;

    private TransactionDetails(
            String id,
            String type,
            String amountInTomans,
            String senderAccountNumber,
            String senderCustomerId,
            String receiverAccountNumber,
            String receiverCustomerId,
            String status,
            String createdAt
    ) {
        this.id = id;
        this.type = type;
        this.amountInTomans = amountInTomans;
        this.senderAccountNumber = senderAccountNumber;
        this.senderCustomerId = senderCustomerId;
        this.receiverAccountNumber = receiverAccountNumber;
        this.receiverCustomerId = receiverCustomerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static TransactionDetails create(Transaction transaction) {
        if(transaction instanceof Deposit) {
            return new TransactionDetails(
                    transaction.getId(),
                    "DEPOSIT",
                    transaction.getAmount().toString(),
                    null,
                    transaction.getSenderAccount().getCustomerId(),
                    null,
                    null,
                    transaction.getStatus().getDisplayValue(),
                    transaction.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
        }
        if(transaction instanceof Withdraw) {
            return new TransactionDetails(
                    transaction.getId(),
                    "WITHDRAW",
                    transaction.getAmount().toString(),
                    null,
                    transaction.getSenderAccount().getCustomerId(),
                    null,
                    null,
                    transaction.getStatus().getDisplayValue(),
                    transaction.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
        }
        if(transaction instanceof PayaTransfer) {
            return new TransactionDetails(
                    transaction.getId(),
                    "PAYA_TRANSFER",
                    transaction.getAmount().toString(),
                    transaction.getSenderAccount().getAccountNumber().toString(),
                    transaction.getSenderAccount().getCustomerId(),
                    ((PayaTransfer) transaction).getReceiverAccount().getAccountNumber().toString(),
                    ((PayaTransfer) transaction).getReceiverAccount().getCustomerId(),
                    transaction.getStatus().getDisplayValue(),
                    transaction.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
        }
        if(transaction instanceof DirectTransfer) {
            return new TransactionDetails(
                    transaction.getId(),
                    "DIRECT_TRANSFER",
                    transaction.getAmount().toString(),
                    transaction.getSenderAccount().getAccountNumber().toString(),
                    transaction.getSenderAccount().getCustomerId(),
                    ((PayaTransfer) transaction).getReceiverAccount().getAccountNumber().toString(),
                    ((PayaTransfer) transaction).getReceiverAccount().getCustomerId(),
                    transaction.getStatus().getDisplayValue(),
                    transaction.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
        }
        return null;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getAmountInTomans() {
        return this.amountInTomans;
    }

    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public String getSenderCustomerId() {
        return this.senderCustomerId;
    }

    public String getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public String getReceiverCustomerId() {
        return this.receiverCustomerId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

}
