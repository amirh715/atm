package com.pmt.atm.infra.DTOs;

public class CustomerDetails {

    private final String customerId;

    private final String username;

    private final String name;

    private final String creditRate;

    private String totalBalanceOfAllAccountsInTomans;

    public CustomerDetails(
            String customerId,
            String username,
            String name,
            String creditRate,
            String totalBalanceOfAllAccountsInTomans
    ) {
        this.customerId = customerId;
        this.username = username;
        this.name = name;
        this.creditRate = creditRate;
        this.totalBalanceOfAllAccountsInTomans = totalBalanceOfAllAccountsInTomans;
    }

    public CustomerDetails() {
        this.customerId = null;
        this.username = null;
        this.name = null;
        this.creditRate = null;
        this.totalBalanceOfAllAccountsInTomans = null;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public String getCreditRate() {
        return this.creditRate;
    }

    public String getTotalBalanceOfAllAccountsInTomans() {
        return this.totalBalanceOfAllAccountsInTomans;
    }

    public void setTotalBalanceOfAllAccountsInTomans(String totalBalanceOfAllAccountsInTomans) {
        this.totalBalanceOfAllAccountsInTomans = totalBalanceOfAllAccountsInTomans;
    }

}