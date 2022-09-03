package com.pmt.atm.domain.exceptions;

public class InsufficientCreditForWithdrawalException extends BusinessException {

    private static final String ERROR_CODE = "Insufficient_Credit_For_Withdrawal_Error";

    private static final String DEFAULT_MESSAGE = "There is not sufficient credit for withdrawal.";

    public InsufficientCreditForWithdrawalException() {
        super(ERROR_CODE, DEFAULT_MESSAGE);
    }

    public InsufficientCreditForWithdrawalException(String message) {
        super(ERROR_CODE, message);
    }

    public InsufficientCreditForWithdrawalException(String message, String description) {
        super(ERROR_CODE, message, description);
    }

}
