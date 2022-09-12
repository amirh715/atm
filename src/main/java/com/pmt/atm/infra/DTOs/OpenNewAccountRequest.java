package com.pmt.atm.infra.DTOs;

public record OpenNewAccountRequest(String customerId, String initialDepositInTomans) {
}
