package com.pmt.atm.infra.persistence.repositories;

import com.pmt.atm.domain.Account;
import com.pmt.atm.domain.AccountNumber;
import com.pmt.atm.domain.Toman;
import com.pmt.atm.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Set<Account> findAccountsByCustomerId(String customerId);

    Optional<Account> findAccountByAccountNumber(AccountNumber accountNumber);

    @Query(value = "SELECT tr.* FROM transactions tr WHERE tr.account_id = ?1 " +
            "AND tr.status = 0 ORDER BY tr.created_at DESC LIMIT 10", nativeQuery = true)
    Set<Transaction> getLastTenTransactionOfAccount(String accountId);

    @Query(value = "SELECT SUM(ac.current_balance) FROM accounts ac WHERE ac.customer_id = ?1", nativeQuery = true)
    Toman getTotalBalanceOfAllAccountsOfCustomer(String customerId);

    // TODO: MySQL datetime comparison
    @Query(value = "SELECT tr.* FROM transactions tr, accounts ac WHERE tr.account_id = ac.id " +
            "AND ac.customer_id = ?1 AND tr.created_at ", nativeQuery = true)
    Set<Transaction> getAllTransactionsOfCustomerInThePastMonth(String customerId);

}
