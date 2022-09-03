package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.ValidationException;

public class Username {

    private final String value;

    private Username(String value) {
        this.value = value;
    }

    public static Username create(String value) {
        if(value == null) throw new ValidationException("Username is required");
        // TODO: Regexp pattern
        return new Username(value);
    }

    public String getValue() {
        return value;
    }

}
