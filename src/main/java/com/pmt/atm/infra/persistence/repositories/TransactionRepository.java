package com.pmt.atm.infra.persistence.repositories;

import com.pmt.atm.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
