package com.pmt.atm.domain;

public class PayaTransfer extends Transfer {

    protected PayaTransfer(Account receiverAccount, Toman amountToTransfer) {
        super(receiverAccount, amountToTransfer);
    }

    @Override
    public Toman getDailyTransferLimitAmount() {
        // TODO: not the best implementation. refactor.
        final CustomerType customerType = getSenderAccount().getCustomerType();
        return customerType.equals(CustomerType.LEGAL_CUSTOMER) ?
                Toman.create(200000000) : Toman.create(50000000);
    }

}
