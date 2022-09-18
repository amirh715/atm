package com.pmt.atm.infra.DTOs;

public class MakePayaTransferRequest {

    private final String senderAccountNumber;

    private final String receiverAccountNumber;

    private final String amountInTomans;


    public MakePayaTransferRequest(String senderAccountNumber, String receiverAccountNumber, String transferAmount) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amountInTomans = transferAmount;
    }

    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public String getAmountInTomans() {
        return this.amountInTomans;
    }

}
