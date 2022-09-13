package com.pmt.atm.infra.DTOs;

public class MakeDepositRequest {

    private final String accountNumber;

    private final String depositAmount;

    public MakeDepositRequest(String accountNumber, String depositAmount) {
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getDepositAmount() {
        return this.depositAmount;
    }

}
