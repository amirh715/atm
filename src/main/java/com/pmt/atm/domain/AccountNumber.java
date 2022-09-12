package com.pmt.atm.domain;

import java.util.Random;

public class AccountNumber {

    private static final int MIN = 10000000;
    private static final int MAX = 99999999;

    private final int value;

    private AccountNumber(int value) {
        this.value = value;
    }

    public static AccountNumber create(int value) {
        return new AccountNumber(value);
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
