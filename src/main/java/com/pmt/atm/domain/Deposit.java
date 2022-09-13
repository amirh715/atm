package com.pmt.atm.domain;

import javax.persistence.Entity;

@Entity
public class Deposit extends Transaction {

    public Deposit(Toman amount) {
        super(amount);
    }

    public Deposit() {

    }

}
