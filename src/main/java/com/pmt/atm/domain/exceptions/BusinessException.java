package com.pmt.atm.domain.exceptions;

public abstract class BusinessException extends RuntimeException {

    private final String errorCode;

    private final String description;

    protected BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.description = "";
    }

    protected BusinessException(String errorCode, String message, String description) {
        super(message);
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

}
