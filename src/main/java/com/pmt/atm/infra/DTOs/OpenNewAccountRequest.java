package com.pmt.atm.infra.DTOs;

public class OpenNewAccountRequest {

    private final String customerId;

    private final String initialDepositAmountInTomans;

    public OpenNewAccountRequest(String customerId, String initialDepositAmountInTomans) {
        this.customerId = customerId;
        this.initialDepositAmountInTomans = initialDepositAmountInTomans;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getInitialDepositAmountInTomans() {
        return this.initialDepositAmountInTomans;
    }

}
