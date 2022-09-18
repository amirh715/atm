package com.pmt.atm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {

    public Deposit(Toman amount) {
        super(amount);
    }

    public Deposit() {

    }

}
