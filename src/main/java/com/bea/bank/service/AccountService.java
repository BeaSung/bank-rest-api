package com.bea.bank.service;

import com.bea.bank.domain.Account;
import com.bea.bank.domain.exception.AccountNotFoundException;
import com.bea.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Account findAccount(String accountNo) {
        return accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(accountNo + "Account Not Found"));
    }

    @Transactional
    public void deleteAccount(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow();
        account.checkClosableAccount();
        accountRepository.delete(account);
    }

    @Transactional
    public Account deposit(String accountNo, Long balance) {
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(accountNo + "Account Not Found"));
        account.deposit(balance);
        accountRepository.save(account);
        return account;
    }
}
