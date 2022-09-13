package com.pmt.atm.infra.controllers.errors;

import com.pmt.atm.domain.exceptions.BusinessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiError {

    private static final String FAILOVER_MESSAGE = "خطایی رخ داده است.";
    private static final String FAILOVER_DESCRIPTION = "لطفا در صورت تکرار خطا با پشتیبانی تماس بگیرید.";

    private final String code;

    private final String message;

    private final String description;

    private final String timestamp;

    public ApiError(BusinessException exception) {
        this.code = exception.getErrorCode();
        this.message = exception.getMessage();
        this.description = exception.getDescription();
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public ApiError(RuntimeException exception) {
        this.code = "Unknown_Exception";
        this.message = FAILOVER_MESSAGE;
        this.description = FAILOVER_DESCRIPTION;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

}
