package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.InsufficientCreditForWithdrawalException;
import com.pmt.atm.infra.persistence.attributeConverter.TomanAttributeConverter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Account {

    @Id
    private final String id;

    @Column(name = "current_balance_in_tomans")
    @Convert(converter = TomanAttributeConverter.class)
    private Toman currentBalance;

    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @ManyToOne
    private final Customer accountHolder;

    @OneToMany
    private final Set<Transaction> transactions = new HashSet<>();

    public Account(
        Customer accountHolder
    ) {
        this.id = UUID.randomUUID().toString();
        this.accountHolder = accountHolder;
        this.currentBalance = Toman.createZero();
        this.createdAt = LocalDateTime.now();
    }

    public Account() {
        this.id = UUID.randomUUID().toString();
        this.accountHolder = null;
        this.createdAt = LocalDateTime.now();
    }

    public boolean hasSufficientBalance(Toman amountToWithdraw) {
        return !currentBalance.minus(amountToWithdraw).isLessThan(Toman.createZero());
    }

    public boolean doesNotHaveSufficientBalance(Toman amountToWithdraw) {
        return !hasSufficientBalance(amountToWithdraw);
    }

    public boolean hasReachedTransactionLimit() {
        return false;
    }

    public void deposit(Transaction transaction) {
        currentBalance = currentBalance.plus(transaction.getAmount());
        transactions.add(transaction);
        modified();
    }

    public void withdraw(Transaction transaction) {
        if(doesNotHaveSufficientBalance(transaction.getAmount())) throw new InsufficientCreditForWithdrawalException();
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

    public Toman getCurrentBalance() {
        return currentBalance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public Set<Transaction> getAllTransactions() {
        return transactions;
    }

    public Set<Transaction> getAllOfTodaysSuccessfulTransactions() {
        // TODO: all transactions --> today's transaction --> today's successful transactions
        return transactions;
    }

}
