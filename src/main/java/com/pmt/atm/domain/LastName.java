package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.ValidationException;

public class LastName {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 80;

    private final String value;

    private LastName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LastName create(String value) {
        if(value == null)
            throw new ValidationException("Lastname is required.");
        if(value.length() < MIN_LENGTH || value.length() > MAX_LENGTH)
            throw new ValidationException("Lastname must be between " + MIN_LENGTH + " to " + MAX_LENGTH + " characters long.");
        return new LastName(value);
    }

}
