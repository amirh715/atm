package com.pmt.atm.domain.CreditCalculation;

import com.pmt.atm.domain.*;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicCustomerCreditCalculationPolicy implements CustomerCreditCalculationPolicy {

    private final int directTransferCreditPercentage;

    private final int payaTransferCreditPercentage;

    public BasicCustomerCreditCalculationPolicy(
            int directTransferCreditPercentage,
            int payaTransferCreditPercentage
    ) {
        this.directTransferCreditPercentage = directTransferCreditPercentage;
        this.payaTransferCreditPercentage = payaTransferCreditPercentage;
    }

    @Override
    public CreditRate calculateCustomerCredit(Set<Transaction> transactions, Toman averageTransactionAmount) {

        CreditRate creditRate = CreditRate.createZero();

        {
            final Set<DirectTransfer> directTransfers =
                    transactions.stream().filter(transaction -> transaction instanceof DirectTransfer)
                            .map(transaction -> (DirectTransfer) transaction)
                            .collect(Collectors.toSet());
            for(DirectTransfer directTransfer : directTransfers) {

            }
        }

        {
            final Set<PayaTransfer> payaTransfers =
                    transactions.stream().filter(transaction -> transaction instanceof PayaTransfer)
                            .map(transaction -> (PayaTransfer) transaction)
                            .collect(Collectors.toSet());
            for(PayaTransfer payaTransfer : payaTransfers) {

            }
        }

        return creditRate;
    }

    public int getDirectTransferCreditPercentage() {
        return this.directTransferCreditPercentage;
    }

    public int getPayaTransferCreditPercentage() {
        return this.payaTransferCreditPercentage;
    }

}
