package com.pmt.atm.domain;

public enum TransactionStatus {

    CREATED("ایجاد شده"),
    FAILED("ناموفق"),
    SUCCEEDED("موفق");

    private final String displayValue;

    TransactionStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public boolean isSuccessful() {
        return this.equals(TransactionStatus.SUCCEEDED);
    }

    public boolean isFailed() {
        return !isSuccessful();
    }

    public boolean isCreated() {
        return this.equals(TransactionStatus.CREATED);
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
