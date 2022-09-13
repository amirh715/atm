package com.pmt.atm.domain;

import javax.persistence.Entity;

@Entity
public class PayaTransfer extends Transfer {

    public PayaTransfer(Account receiverAccount, Toman amountToTransfer) {
        super(receiverAccount, amountToTransfer);
    }

    public PayaTransfer() {
        super(null, null);
    }

    @Override
    public Toman getDailyTransferLimitAmount() {
        // TODO: bad implementation. refactor.
        final CustomerType customerType = getSenderAccount().getCustomerType();
        return customerType.equals(CustomerType.LEGAL_CUSTOMER) ?
                Toman.create(200000000) : Toman.create(50000000);
    }

}
