package com.pmt.atm.domain;

public abstract class Transfer extends Transaction {

    private final Account receiverAccount;

    protected Transfer(Account receiverAccount, Toman amountToTransfer) {
        super(amountToTransfer);
        this.receiverAccount = receiverAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

}
