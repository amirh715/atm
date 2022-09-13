package com.pmt.atm.domain.CreditCalculation;

import com.pmt.atm.domain.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicCustomerCreditCalculationPolicy implements CustomerCreditCalculationPolicy {

    private final Toman directTransferAmountPerPoint;

    private final Toman payaTransferAmountPerPoint;

    public BasicCustomerCreditCalculationPolicy(
            Toman directTransferAmountPerPoint,
            Toman payaTransferAmountPerPoint
    ) {
        this.directTransferAmountPerPoint = directTransferAmountPerPoint;
        this.payaTransferAmountPerPoint = payaTransferAmountPerPoint;
    }

    @Override
    public CreditRate calculateCustomerCredit(Set<Transaction> transactions) {

        CreditRate totalCreditRate = CreditRate.createZero();
        final LocalDate oneMonthAgo = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        {
            final Set<DirectTransfer> directTransfers =
                    transactions.stream()
                            .filter(transaction ->
                                    transaction.getCreatedAt().toLocalDate().isAfter(oneMonthAgo))
                            .filter(transaction -> transaction instanceof DirectTransfer)
                            .map(directTransfer -> (DirectTransfer) directTransfer)
                            .filter(Transfer::isSuccessful)
                            .collect(Collectors.toSet());
            for(DirectTransfer directTransfer : directTransfers) {
                final Toman amount = directTransfer.getAmount();
                final CreditRate creditRate = CreditRate.create(
                        amount.getValue() / directTransferAmountPerPoint.getValue()
                );
                totalCreditRate = totalCreditRate.plus(creditRate);
            }
        }

        {
            final Set<PayaTransfer> payaTransfers =
                    transactions.stream()
                            .filter(transaction ->
                                    transaction.getCreatedAt().toLocalDate().isAfter(oneMonthAgo))
                            .filter(transaction -> transaction instanceof PayaTransfer)
                            .map(payaTransfer -> (PayaTransfer) payaTransfer)
                            .filter(Transfer::isSuccessful)
                            .collect(Collectors.toSet());
            for(PayaTransfer payaTransfer : payaTransfers) {
                final Toman amount = payaTransfer.getAmount();
                final CreditRate creditRate = CreditRate.create(
                        amount.getValue() / payaTransferAmountPerPoint.getValue()
                );
                totalCreditRate = totalCreditRate.plus(creditRate);
            }
        }

        return totalCreditRate;
    }

}
