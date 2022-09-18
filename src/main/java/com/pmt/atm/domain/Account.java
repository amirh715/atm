package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.AccountIsNotAuthorizedForTransferException;
import com.pmt.atm.domain.exceptions.InsufficientCreditForWithdrawalException;
import com.pmt.atm.domain.Specifications.DailyTransferLimitSpecification;
import com.pmt.atm.domain.Specifications.SufficientAccountBalanceSpecification;
import com.pmt.atm.infra.persistence.attributeConverter.AccountNumberAttributeConverter;
import com.pmt.atm.infra.persistence.attributeConverter.TomanAttributeConverter;
import com.pmt.atm.utils.specification.AndSpecification;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "accounts")
public class Account {

    @Id
    private final String id;

    @Column(name = "account_number", unique = true)
    @Convert(converter = AccountNumberAttributeConverter.class)
    private final AccountNumber accountNumber;

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

    // TODO: refactor for receiver
    @OneToMany(mappedBy = "initiatorAccount", cascade = CascadeType.ALL)
    private final Set<Transaction> transactions = new HashSet<>();

    public Account(
        String customerId
    ) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.currentBalance = Toman.createZero();
        this.createdAt = LocalDateTime.now();
        this.accountNumber = AccountNumber.createNew();
        this.customerType = null;
    }

    public Account() {
        this.id = UUID.randomUUID().toString();
        this.customerId = null;
        this.createdAt = LocalDateTime.now();
        this.accountNumber = null;
        this.customerType = null;
    }

    private boolean isAuthorizedForTransfer(Transfer transfer) {
        final SufficientAccountBalanceSpecification sufficientAccountBalanceSpecification =
                new SufficientAccountBalanceSpecification(currentBalance);
        final DailyTransferLimitSpecification dailyTransferLimitSpecification =
                new DailyTransferLimitSpecification(getAllOfTodaysSuccessfulTransfers());
        System.out.println("isAuthorizedForTransfer:: " + sufficientAccountBalanceSpecification.isSatisfiedBy(transfer) + " - " + dailyTransferLimitSpecification.isSatisfiedBy(transfer));
        return new AndSpecification(sufficientAccountBalanceSpecification, dailyTransferLimitSpecification)
                .isSatisfiedBy(transfer);
    }

    public boolean isNotAuthorizedForTransfer(Transfer transfer) {
        return !isAuthorizedForTransfer(transfer);
    }

    private boolean hasSufficientBalance(Transaction transaction) {
        return new SufficientAccountBalanceSpecification(currentBalance).isSatisfiedBy(transaction);
    }

    private boolean doesNotHaveSufficientBalance(Transaction transaction) {
        return !hasSufficientBalance(transaction);
    }

    public void deposit(Deposit aDeposit) {
        currentBalance = currentBalance
                .plus(aDeposit.getAmount());
//        deposits.add(aDeposit);
        transactions.add(aDeposit);
        aDeposit.setInitiatorAccount(this);
        aDeposit.markTransactionAsSucceeded();
        modified();
    }

    public void deposit(Transfer aTransfer) {
        if(!aTransfer.getReceiverAccount().equals(this)) throw new RuntimeException("1111");
        currentBalance = currentBalance
                .plus(aTransfer.getAmount());
//        initiatedTransfers.add(aTransfer);
        transactions.add(aTransfer);
        modified();
    }

    public void withdraw(Withdraw aWithdraw) {
        if(doesNotHaveSufficientBalance(aWithdraw)) throw new InsufficientCreditForWithdrawalException();
        currentBalance = currentBalance.minus(aWithdraw.getAmount());
//        withdraws.add(aWithdraw);
        transactions.add(aWithdraw);
        aWithdraw.setInitiatorAccount(this);
        aWithdraw.markTransactionAsSucceeded();
        modified();
    }

    public void withdraw(Transfer aTransfer) {
        aTransfer.setInitiatorAccount(this);
        if(!aTransfer.getInitiatorAccount().equals(this)) throw new RuntimeException();
        if(isNotAuthorizedForTransfer(aTransfer)) throw new AccountIsNotAuthorizedForTransferException();
        currentBalance = currentBalance.minus(aTransfer.getAmount());
//        receivedTransfers.add(aTransfer);
        transactions.add(aTransfer);
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

    public String getCustomerId() {
        return customerId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

//    public Set<Transaction> getAllTransactions() {
//        final Set<Transaction> allTransactions = new HashSet<>();
//        allTransactions.addAll(deposits);
//        allTransactions.addAll(withdraws);
//        allTransactions.addAll(initiatedTransfers);
//        allTransactions.addAll(receivedTransfers);
//        return allTransactions;
//    }

    public Set<Transaction> getAllOfTodaysSuccessfulTransactions() {
//        return getAllTransactions()
        return transactions
                .stream().filter(transaction -> transaction.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                .filter(Transaction::isSuccessful)
                .collect(Collectors.toSet());
    }

    public Set<Transfer> getAllOfTodaysSuccessfulTransfers() {
//        return getAllTransactions()
        return transactions
                .stream().filter(transaction -> transaction instanceof Transfer)
                .map(transaction -> (Transfer) transaction)
                .filter(transaction -> transaction.getCreatedAt().toLocalDate().equals(LocalDate.now()))
                .filter(Transfer::isSuccessful)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
