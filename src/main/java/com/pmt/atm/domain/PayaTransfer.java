package com.pmt.atm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PAYA_TRANSFER")
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
        final CustomerType customerType = getInitiatorAccount().getCustomerType();
        return customerType.equals(CustomerType.LEGAL_CUSTOMER) ?
                Toman.create(200000000) : Toman.create(50000000);
    }

}
