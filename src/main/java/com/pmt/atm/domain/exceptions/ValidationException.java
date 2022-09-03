package com.pmt.atm.domain.exceptions;

public class ValidationException extends BusinessException {

    private static final String ERROR_CODE = "VALIDATION_ERROR";

    public ValidationException(String message) {
        super(ERROR_CODE, message);
    }

    public ValidationException(String message, String description) {
        super(ERROR_CODE, message, description);
    }

}
