package com.pmt.atm.domain.exceptions;

public class AccountIsNotAuthorizedForTransferException extends BusinessException {

    private static final String ERROR_CODE = "Account_Is_Not_Authorized_For_Transfer_Error";
    private static final String DEFAULT_MESSAGE = "Account is not authorized for transfer.";

    public AccountIsNotAuthorizedForTransferException() {
        super(ERROR_CODE, DEFAULT_MESSAGE);
    }

    public AccountIsNotAuthorizedForTransferException(String message) {
        super(ERROR_CODE, message);
    }

    public AccountIsNotAuthorizedForTransferException(String message, String description) {
        super(ERROR_CODE, message, description);
    }

}
