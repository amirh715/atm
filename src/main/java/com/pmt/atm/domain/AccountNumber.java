package com.pmt.atm.domain;

import javax.xml.bind.ValidationException;
import java.util.Random;

public class AccountNumber {

    private static final int MIN = 10000000;
    private static final int MAX = 99999999;

    private final int value;

    private AccountNumber(int value) {
        this.value = value;
    }

    public static AccountNumber create(int value) throws ValidationException {
        if(value < MIN || value > MAX)
            throw new ValidationException("Account number must be between " + MIN + " and " + MAX);
        return new AccountNumber(value);
    }

    public static AccountNumber create(String value) throws ValidationException {
        try {
            return create(Integer.parseInt(value));
        } catch(NumberFormatException exception) {
            throw new ValidationException("Account number must be a positive integer.");
        }
    }

    public static AccountNumber createNew() {
        return new AccountNumber(generateRandomNumber());
    }

    public int getValue() {
        return value;
    }

    private static int generateRandomNumber() {
        return new Random().nextInt(MAX - MIN + 1) + MIN;
    }

}
