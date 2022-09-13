package com.pmt.atm.infra.DTOs;

import com.pmt.atm.domain.Account;

public class AccountDetails {

    private final String accountId;

    private final String accountNumber;

    private final String customerId;

    private final String balanceInTomans;

    private AccountDetails(
            String accountId,
            String accountNumber,
            String customerId,
            String balanceInTomans
    ) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balanceInTomans = balanceInTomans;
    }

    public static AccountDetails create(Account account) {
        return new AccountDetails(
                account.getId(),
                String.valueOf(account.getAccountNumber().getValue()),
                account.getCustomerId(),
                String.valueOf(account.getCurrentBalance().getValue())
        );
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getBalanceInTomans() {
        return this.balanceInTomans;
    }

}
