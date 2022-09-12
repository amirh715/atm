package com.pmt.atm.infra.persistence.repositories;

import com.pmt.atm.domain.Toman;
import com.pmt.atm.domain.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query(
            value = "SELECT tr.* FROM transactions tr " +
                    "WHERE tr.accountId = ?1 " +
                    "AND tr.status = 1 " + // TODO
                    "ORDER BY tr.created_at DESC LIMIT 10",
            nativeQuery = true
    )
    public List<Transaction> getTheLastTenSuccessfulTransactionsOfAccount(String accountId);

    @Query(value = "", nativeQuery = true)
    public Toman getAverageTransactionAmountOfAllCustomersInThePastYear();

}
