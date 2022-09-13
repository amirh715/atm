package com.pmt.atm.infra.controllers;

import com.pmt.atm.domain.*;
import com.pmt.atm.domain.CreditCalculation.CustomerCreditCalculationPolicy;
import com.pmt.atm.domain.exceptions.AccountDoesNotExistException;
import com.pmt.atm.infra.DTOs.*;
import com.pmt.atm.infra.persistence.repositories.AccountRepository;
import com.pmt.atm.services.CustomerProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import javax.xml.bind.ValidationException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class Controller {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerProxyService customerProxyService;

    @Autowired
    private CustomerCreditCalculationPolicy customerCreditCalculationPolicy;

    @PostMapping("/open")
    public void openNewAccount(@RequestBody OpenNewAccountRequest request) {

        final CustomerDetailsResponse customerDetails =
                customerProxyService.getCustomerDetailsById(request.getCustomerId());

        final Account account = new Account(request.getCustomerId());

        final Toman initialDepositAmount =
                request.getInitialDepositAmountInTomans() != null ?
                        Toman.create(request.getInitialDepositAmountInTomans()) :
                        Toman.createZero();

        final Deposit aDeposit = new Deposit(initialDepositAmount);
        account.deposit(aDeposit);

        accountRepository.save(account);

    }

    @PostMapping("/deposit")
    public void makeDeposit(@RequestBody MakeDepositRequest request) throws ValidationException {

        final AccountNumber accountNumber = AccountNumber.create(request.getAccountNumber());
        final Toman depositAmount = Toman.create(request.getDepositAmount());

        final Account account = accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(AccountDoesNotExistException::new);

        final Deposit aDeposit = new Deposit(depositAmount);
        account.deposit(aDeposit);

        accountRepository.save(account);

    }

    @PostMapping("/withdraw")
    public void makeWithdraw(@RequestBody MakeWithdrawRequest request) throws ValidationException {

        final AccountNumber accountNumber = AccountNumber.create(request.getAccountNumber());
        final Toman withdrawAmount = Toman.create(request.getWithdrawAmount());

        final Account account = accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(AccountDoesNotExistException::new);

        final Withdraw aWithdraw = new Withdraw(withdrawAmount);
        account.withdraw(aWithdraw);

        accountRepository.save(account);

    }

    @PostMapping("/paya")
    @Transactional
    public void makePayaTransfer(@RequestBody MakePayaTransferRequest request) throws ValidationException {

        final AccountNumber senderAccountNumber = AccountNumber.create(request.getSenderAccountNumber());
        final AccountNumber receiverAccountNumber = AccountNumber.create(request.getReceiverAccountNumber());
        final Toman transferAmount = Toman.create(request.getTransferAmount());

        final Account senderAccount = accountRepository.findAccountByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new AccountDoesNotExistException("Sender account does not exist."));
        final Account receiverAccount = accountRepository.findAccountByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new AccountDoesNotExistException("Receiver account does not exist."));

        final PayaTransfer payaTransfer = new PayaTransfer(receiverAccount, transferAmount);

        senderAccount.makeTransfer(payaTransfer);

        accountRepository.save(senderAccount);

        receiverAccount.deposit(payaTransfer.buildReceiverDeposit());
        accountRepository.save(receiverAccount);

    }

    @PostMapping("/direct")
    public void makeDirectTransfer(@RequestBody MakeDirectTransferRequest request) throws ValidationException {

        final AccountNumber senderAccountNumber = AccountNumber.create(request.getSenderAccountNumber());
        final AccountNumber receiverAccountNumber = AccountNumber.create(request.getReceiverAccountNumber());
        final Toman transferAmount = Toman.create(request.getTransferAmountInTomans());

        final Account senderAccount = accountRepository.findAccountByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new AccountDoesNotExistException("Sender account does not exist."));
        final Account receiverAccount = accountRepository.findAccountByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new AccountDoesNotExistException("Receiver account does not exist."));

        final DirectTransfer directTransfer = new DirectTransfer(senderAccount, transferAmount);

        senderAccount.makeTransfer(directTransfer);

        accountRepository.save(senderAccount);

        receiverAccount.deposit(directTransfer.buildReceiverDeposit());
        accountRepository.save(receiverAccount);

    }

    @GetMapping("/details")
    public AccountDetails getAccountDetails(@QueryParam("accountId") String accountId) {

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(AccountDoesNotExistException::new);

        return AccountDetails.create(account);

    }

    @GetMapping("/")
    public Set<AccountDetails> getAllOfCustomersAccounts(@QueryParam("customerId") String customerId) {

        return accountRepository.findAccountsByCustomerId(customerId)
                .stream().map(AccountDetails::create)
                .collect(Collectors.toSet());

    }

    @GetMapping("/last_ten_transactions")
    public Set<TransactionDetails> getTheLastTenTransactionsOfAccount(@QueryParam("accountId") String accountId) {

        return accountRepository.getLastTenTransactionOfAccount(accountId)
                .stream().map(TransactionDetails::create)
                .collect(Collectors.toSet());

    }

    @GetMapping("/balance")
    public int getCurrentBalanceOfAccount(@QueryParam("accountId") String accountId) {

        return accountRepository.findById(accountId)
                .map(account -> account.getCurrentBalance().getValue())
                .orElseThrow(AccountDoesNotExistException::new);

    }

    @GetMapping("/balanceOfAllAccountsOfCustomer")
    public int getTotalBalanceOfCustomersAccounts(@QueryParam("customerId") String customerId) {

        return accountRepository.getTotalBalanceOfAllAccountsOfCustomer(customerId)
                .getValue();

    }

    @GetMapping("/creditRate")
    public CreditRate calculateCustomerCredit(@QueryParam("customerId") String customerId) {

        final Set<Transaction> transactions = accountRepository.getAllTransactionsOfCustomerInThePastMonth(customerId);

        return customerCreditCalculationPolicy.calculateCustomerCredit(transactions);

    }

}
