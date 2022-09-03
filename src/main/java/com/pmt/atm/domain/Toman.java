package com.pmt.atm.domain;

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

    public boolean isZero() {
        return value == 0;
    }

    public boolean isLessThan(Toman amountToCompare) {
        return value < amountToCompare.value;
    }

    public boolean isMoreThan(Toman amountToCompare) {
        return value > amountToCompare.value;
    }

    public static Toman create(int value) {
        return new Toman(value);
    }

    public static Toman createZero() {
        return new Toman(0);
    }

}
