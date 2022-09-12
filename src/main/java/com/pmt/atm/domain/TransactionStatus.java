package com.pmt.atm.domain;

public enum TransactionStatus {

    CREATED,
    FAILED,
    SUCCEEDED;

    public boolean isSuccessful() {
        return this.equals(TransactionStatus.SUCCEEDED);
    }

    public boolean isFailed() {
        return !isSuccessful();
    }

}
