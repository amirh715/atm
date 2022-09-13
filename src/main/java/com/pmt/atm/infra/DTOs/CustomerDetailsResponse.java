package com.pmt.atm.infra.DTOs;

public class CustomerDetailsResponse {

    private final String customerId;

    public CustomerDetailsResponse(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

}
