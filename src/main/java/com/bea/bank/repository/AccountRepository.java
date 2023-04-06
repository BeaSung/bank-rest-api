package com.bea.bank.repository;

import com.bea.bank.domain.Account;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface AccountRepository extends ListCrudRepository<Account, Long> {

    Optional<Account> findByAccountNo(String accountNo);
}
