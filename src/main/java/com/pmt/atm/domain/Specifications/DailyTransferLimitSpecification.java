package com.pmt.atm.domain.Specifications;

import com.pmt.atm.domain.*;
import com.pmt.atm.utils.specification.AbstractSpecification;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class DailyTransferLimitSpecification extends AbstractSpecification<Transfer> {

    private final Set<Transfer> todaysSuccessfulTransfers;

    public DailyTransferLimitSpecification(Set<Transfer> todaysTransfers) {
        this.todaysSuccessfulTransfers = todaysTransfers
                .stream().filter(transfer -> transfer.getCreatedAt().toLocalDate().isEqual(LocalDate.now()))
                .filter(Transfer::isSuccessful)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isSatisfiedBy(Transfer transfer) {
        final CustomerType customerType = transfer.getSenderAccount().getCustomerType();
        return
                totalAmountOfTodaysSuccessfulTransfers().plus(transfer.getAmount())
                        .isMoreThan(transfer.getDailyTransferLimitAmount());
    }

    private final Toman totalAmountOfTodaysSuccessfulTransfers() {
        return todaysSuccessfulTransfers
                .stream().filter(Transfer::isSuccessful)
                .map(Transaction::getAmount)
                .reduce(Toman.createZero(), (subTotal, amount) -> subTotal.plus(amount));
    }

}
