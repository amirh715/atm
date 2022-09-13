package com.pmt.atm.infra.DTOs;

public class MakeDirectTransferRequest {

    private final String senderAccountNumber;

    private final String receiverAccountNumber;

    private final String transferAmountInTomans;


    public MakeDirectTransferRequest(String senderAccountNumber, String receiverAccountNumber, String transferAmountInTomans) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.transferAmountInTomans = transferAmountInTomans;
    }

    public String getSenderAccountNumber() {
        return this.senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public String getTransferAmountInTomans() {
        return this.transferAmountInTomans;
    }

}
