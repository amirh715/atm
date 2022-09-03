package com.pmt.atm.services;

import com.pmt.atm.domain.*;
import com.pmt.atm.domain.exceptions.AccountDoesNotExistException;
import com.pmt.atm.infra.persistence.repositories.AccountRepository;
import com.pmt.atm.infra.persistence.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class TransactionCommandService {

    @Autowired
    private AccountRepository accountRepository;

    public void deposit(String accountId, Toman depositAmount) {

        final Account account = accountRepository.findById(accountId).orElseThrow();

//        account.deposit();
        accountRepository.save(account);

    }

    @Transactional
    public void transfer(Toman amountToTransfer, String senderAccountId, String receiverAccountId) {

        final Account senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(AccountDoesNotExistException::new);
        final Account receiverAccount = accountRepository.findById(receiverAccountId)
                .orElseThrow(AccountDoesNotExistException::new);

//        final Transfer transfer = new Transfer(receiverAccount, amountToTransfer);

//        senderAccount.withdraw(transfer);
//        receiverAccount.deposit(transfer);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

    }

    @Transactional
    public void deposit(Toman amountToDeposit, String accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountDoesNotExistException::new);

//        final Deposit deposit;
//
//        account.deposit(deposit);

        accountRepository.save(account);

    }

    @Transactional
    public void withdraw(Toman amountToWithdraw, String accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountDoesNotExistException::new);

//        final Withdraw withdraw;

//        account.withdraw(withdraw);

        accountRepository.save(account);

    }

}
