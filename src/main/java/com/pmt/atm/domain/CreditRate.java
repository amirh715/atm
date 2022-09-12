package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.ValidationException;

public class CreditRate {

    private final float value;

    private static final float MIN = 0;
    private static final float MAX = 150;

    private CreditRate(float value) {
        this.value = value;
    }

    public static CreditRate create(float value) {
        if(value < MIN || value > MAX)
            throw new ValidationException("Credit rate must be between " + MIN + " and " + MAX);
        return new CreditRate(value);
    }

    public static CreditRate createZero() {
        return new CreditRate(0);
    }

    public float getValue() {
        return value;
    }

}
