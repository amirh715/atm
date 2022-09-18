package com.pmt.atm.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public abstract class Transfer extends Transaction {

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiverAccount;

    protected Transfer(Account receiverAccount, Toman amountToTransfer) {
        super(amountToTransfer);
        this.receiverAccount = receiverAccount;
    }

    public Transfer() {
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public abstract Toman getDailyTransferLimitAmount();

}
