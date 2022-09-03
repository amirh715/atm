package com.pmt.atm.infra.persistence.repositories;

import com.pmt.atm.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

}
