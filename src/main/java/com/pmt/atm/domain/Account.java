package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.InsufficientCreditForWithdrawalException;
import com.pmt.atm.domain.specifications.DailyTransferLimitSpecification;
import com.pmt.atm.domain.specifications.SufficientAccountBalanceSpecification;
import com.pmt.atm.infra.persistence.attributeConverter.AccountNumberAttributeConverter;
import com.pmt.atm.infra.persistence.attributeConverter.TomanAttributeConverter;
import com.pmt.atm.utils.specification.AndSpecification;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "accounts")
public class Account {

    @Id
    private final String id;

    @Column(name = "account_number", unique = true)
    @Convert(converter = AccountNumberAttributeConverter.class)
    private AccountNumber accountNumber;

    @Column(name = "current_balance_in_tomans")
    @Convert(converter = TomanAttributeConverter.class)
    private Toman currentBalance;

    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "customer_id")
    private final String customerId;

    @Enumerated
    private final CustomerType customerType;

    @OneToMany
    private final Set<Transaction> transactions = new HashSet<>();

    public Account(
        String customerId
    ) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.currentBalance = Toman.createZero();
        this.createdAt = LocalDateTime.now();
        this.customerType = null;
    }

    public Account() {
        this.id = UUID.randomUUID().toString();
        this.customerId = null;
        this.createdAt = LocalDateTime.now();
        this.customerType = null;
    }

    private boolean isAuthorizedForTransfer(Transfer transfer) {
        final SufficientAccountBalanceSpecification sufficientAccountBalanceSpecification =
                new SufficientAccountBalanceSpecification(currentBalance);
        final DailyTransferLimitSpecification dailyTransferLimitSpecification =
                new DailyTransferLimitSpecification(getAllOfTodaysSuccessfulTransfers());
        return new AndSpecification(sufficientAccountBalanceSpecification, dailyTransferLimitSpecification)
                .isSatisfiedBy(transfer);
    }

    private boolean hasSufficientBalance(Transaction transaction) {
        return new SufficientAccountBalanceSpecification(currentBalance).isSatisfiedBy(transaction);
    }

    private boolean doesNotHaveSufficientBalance(Transaction transaction) {
        return !hasSufficientBalance(transaction);
    }

    public void deposit(Transaction transaction) {
        currentBalance = currentBalance
                .plus(transaction.getAmount());
        transactions.add(transaction);
        modified();
    }

    public void withdraw(Transaction transaction) {
        if(doesNotHaveSufficientBalance(transaction)) throw new InsufficientCreditForWithdrawalException();
        currentBalance = currentBalance.minus(transaction.getAmount());
        transactions.add(transaction);
        modified();
    }

    public void modified() {
        lastModifiedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public Toman getCurrentBalance() {
        return currentBalance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public String getAccountHolderId() {
        return customerId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Set<Transaction> getAllTransactions() {
        return transactions;
    }

    public Set<Transaction> getAllOfTodaysSuccessfulTransactions() {
        return transactions
                .stream().filter(transaction -> transaction.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                .filter(transaction -> transaction.getStatus().isSuccessful())
                .collect(Collectors.toSet());
    }

    public Set<Transfer> getAllOfTodaysSuccessfulTransfers() {
        return transactions
                .stream().filter(transaction -> transaction instanceof Transfer)
                .map(transaction -> (Transfer) transaction)
                .filter(transaction -> transaction.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                .filter(transfer -> transfer.getStatus().isSuccessful())
                .collect(Collectors.toSet());
    }

}
