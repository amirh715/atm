package com.pmt.atm.infra.DTOs;

public class MakeWithdrawRequest {

    private final String accountNumber;

    private final String withdrawAmount;


    public MakeWithdrawRequest(String accountNumber, String withDrawAmount) {
        this.accountNumber = accountNumber;
        this.withdrawAmount = withDrawAmount;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getWithdrawAmount() {
        return this.withdrawAmount;
    }

}
