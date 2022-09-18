package com.pmt.atm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAW")
public class Withdraw extends Transaction {

    public Withdraw(Toman amount) {
        super(amount);
    }

    public Withdraw() {

    }

}
