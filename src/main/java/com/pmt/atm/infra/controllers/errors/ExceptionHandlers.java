package com.pmt.atm.infra.controllers.errors;

import com.pmt.atm.domain.exceptions.*;
import com.pmt.atm.infra.exceptions.InterServiceCommunicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(AccountDoesNotExistException.class)
    public ResponseEntity<ApiError> handle(AccountDoesNotExistException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(AccountIsNotAuthorizedForTransferException.class)
    public ResponseEntity<ApiError> handle(AccountIsNotAuthorizedForTransferException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @ExceptionHandler(InsufficientCreditForWithdrawalException.class)
    public ResponseEntity<ApiError> handle(InsufficientCreditForWithdrawalException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

    @ExceptionHandler(PasswordIsIncorrectException.class)
    public ResponseEntity<ApiError> handle(PasswordIsIncorrectException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handle(ValidationException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(InterServiceCommunicationException.class)
    public ResponseEntity<ApiError> handle(InterServiceCommunicationException exception) {
        final ApiError errorDTO = new ApiError(exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }

}
