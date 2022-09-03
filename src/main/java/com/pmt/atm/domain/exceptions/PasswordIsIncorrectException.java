package com.pmt.atm.domain.exceptions;

public class PasswordIsIncorrectException extends BusinessException {

    private static final String ERROR_CODE = "Password_Is_Incorrect_Error";

    private static final String DEFAULT_MESSAGE = "Password is incorrect.";

    public PasswordIsIncorrectException() {
        super(ERROR_CODE, DEFAULT_MESSAGE);
    }

    public PasswordIsIncorrectException(String message) {
        super(ERROR_CODE, message);
    }

    public PasswordIsIncorrectException(String message, String description) {
        super(ERROR_CODE, message, description);
    }

}
