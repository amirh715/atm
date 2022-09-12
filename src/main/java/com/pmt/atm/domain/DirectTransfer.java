package com.pmt.atm.domain;

public class DirectTransfer extends Transfer {

    protected DirectTransfer(Account receiverAccount, Toman amountToTransfer) {
        super(receiverAccount, amountToTransfer);
    }

    @Override
    public Toman getDailyTransferLimitAmount() {
        return Toman.create(10000000);
    }

}
