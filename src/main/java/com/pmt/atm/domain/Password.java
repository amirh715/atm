package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.ValidationException;

public class Password {

    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 150;
    // TODO: Add PasswordEncoder

    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password createFromPlainPassword(String plainPassword) throws ValidationException {
        if(plainPassword == null) throw new ValidationException("Password is required");
        if(plainPassword.length() < MIN_LENGTH || plainPassword.length() > MAX_LENGTH)
            throw new ValidationException("Password length must be between " + MIN_LENGTH + " to " + MAX_LENGTH + " characters long.");
        return new Password(plainPassword); // return hashed password
    }

    public static Password createFromHashedPassword(String passwordHash) throws ValidationException {
        return new Password(passwordHash);
    }

    public String getValue() {
        return value;
    }

}
