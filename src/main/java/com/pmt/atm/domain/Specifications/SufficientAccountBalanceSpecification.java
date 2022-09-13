package com.pmt.atm.domain.Specifications;

import com.pmt.atm.domain.Toman;
import com.pmt.atm.domain.Transaction;
import com.pmt.atm.utils.specification.AbstractSpecification;

public class SufficientAccountBalanceSpecification extends AbstractSpecification<Transaction> {

    private final Toman accountBalance;

    public SufficientAccountBalanceSpecification(Toman accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean isSatisfiedBy(Transaction transfer) {
        return accountBalance.isMoreThanOrEqualTo(transfer.getAmount());
    }

}
