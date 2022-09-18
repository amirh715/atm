package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.ValidationException;

public class Toman {

    private final int value;

    private Toman(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Toman plus(Toman amountToAdd) {
        return new Toman(value + amountToAdd.value);
    }

    public Toman minus(Toman amountToSubtract) {
        return new Toman(value - amountToSubtract.value);
    }

    public Toman multiply(int multiplicationValue) {
        return new Toman(value * multiplicationValue);
    }

    public Toman divide(int divisionValue) {
        return new Toman(value / divisionValue);
    }

    public Toman calculatePercentage(int percentage) {
        return multiply(percentage).divide(100);
    }

    public boolean isZero() {
        return value == 0;
    }

    public boolean isLessThan(Toman amountToCompare) {
        return value < amountToCompare.value;
    }

    public boolean isMoreThan(Toman amountToCompare) {
        return value > amountToCompare.value;
    }

    public boolean isLessThanOrEqualTo(Toman amountToCompare) {
        return isLessThan(amountToCompare) || equals(amountToCompare);
    }

    public boolean isMoreThanOrEqualTo(Toman amountToCompare) {
        return isMoreThan(amountToCompare) || equals(amountToCompare);
    }

    public static Toman create(int value) {
        if(value < 0) throw new ValidationException("Initial value cannot be negative");
        return new Toman(value);
    }

    public static Toman create(String value) {
        try {
            return create(Integer.parseInt(value));
        } catch(NumberFormatException e) {
            throw new ValidationException("Value must be a valid positive integer.");
        }
    }

    public static Toman createZero() {
        return new Toman(0);
    }

}
