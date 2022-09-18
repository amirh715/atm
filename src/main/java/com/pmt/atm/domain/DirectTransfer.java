package com.pmt.atm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DIRECT_TRANSFER")
public class DirectTransfer extends Transfer {

    public DirectTransfer(Account receiverAccount, Toman amountToTransfer) {
        super(receiverAccount, amountToTransfer);
    }

    public DirectTransfer() {
        super(null, null);
    }

    @Override
    public Toman getDailyTransferLimitAmount() {
        return Toman.create(10000000);
    }

}
