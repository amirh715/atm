package com.pmt.atm.domain.exceptions;

public class AccountDoesNotExistException extends BusinessException {

    private static final String ERROR_CODE = "Account_Does_Not_Exist_Error";

    private static final String DEFAULT_MESSAGE = "Account does not exist.";

    public AccountDoesNotExistException() {
        super(ERROR_CODE, DEFAULT_MESSAGE);
    }

    public AccountDoesNotExistException(String message) {
        super(ERROR_CODE, message);
    }

    public AccountDoesNotExistException(String message, String description) {
        super(ERROR_CODE, message, description);
    }

}
