package com.pmt.atm.infra.exceptions;

public class InterServiceCommunicationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Destination service is unavailable.";

    public InterServiceCommunicationException() {
        super(DEFAULT_MESSAGE);
    }

    public InterServiceCommunicationException(RuntimeException exception) {
        super(exception.getMessage());
    }

    public InterServiceCommunicationException(String message) {
        super(message);
    }

}
