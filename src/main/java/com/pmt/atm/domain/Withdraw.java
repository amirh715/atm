package com.pmt.atm.domain;

import javax.persistence.Entity;

@Entity
public class Withdraw extends Transaction {

    public Withdraw(Toman amount) {
        super(amount);
    }

    public Withdraw() {

    }

}
