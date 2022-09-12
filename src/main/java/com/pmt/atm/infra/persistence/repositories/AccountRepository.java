package com.pmt.atm.infra.persistence.repositories;

import com.pmt.atm.domain.Account;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface AccountRepository extends CrudRepository<Account, String> {

    Set<Account> getAccountsByCustomerId(String customerId);

}
