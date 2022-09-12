package com.pmt.atm.infra.controllers;

import com.pmt.atm.domain.Account;
import com.pmt.atm.domain.Deposit;
import com.pmt.atm.domain.Toman;
import com.pmt.atm.infra.DTOs.OpenNewAccountRequest;
import com.pmt.atm.infra.persistence.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Controller {

    @Autowired
    private AccountRepository accountRepository;

    public void openNewAccount(OpenNewAccountRequest request) {

        final Account account = new Account(request.customerId());

        final Optional<String> initialDepositInTomansOrEmpty = Optional.ofNullable(request.initialDepositInTomans());
        initialDepositInTomansOrEmpty.ifPresent(initialDepositInTomans -> {
            final Toman initialDepositAmount = Toman.create(request.initialDepositInTomans());
            final Deposit deposit = new Deposit();
        });

    }

    public void makeDeposit() {

    }

    public void makeWithdraw() {

    }

    public void makePayaTransfer() {

    }

    public void makeDirectTransfer() {

    }

    public void getAccountDetails() {

    }

    public void getAllOfCustomersAccounts() {

    }

    public void getTheLastTenTransactionsOfAccount() {

    }

    public void getCurrentBalanceOfAccount() {

    }

    public void getTotalBalanceOfCustomersAccounts() {

    }

    public void calculateCustomerCredit() {

    }

}
