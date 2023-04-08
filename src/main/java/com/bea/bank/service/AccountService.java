package com.bea.bank.service;

import com.bea.bank.domain.Account;
import com.bea.bank.domain.exception.AccountNotFoundException;
import com.bea.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountNoGenerator accountNoGenerator;

    @Transactional
    public void createAccount(String name) {
        String accountNo = accountNoGenerator.generate();
        Account account = new Account(accountNo, name);
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
        account.checkCancelableAccount();
        accountRepository.delete(account);
    }

    @Transactional
    public void deposit(String accountNo, Long balance) {
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(accountNo + "Account Not Found"));
        account.deposit(balance);
        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(String accountNo, Long balance) {
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(accountNo + "Account Not Found"));
        account.withdraw(balance);
        accountRepository.save(account);
    }

    @Transactional
    public void transfer(String accountNo, String receivingAccountNo, Long balance) {
        Account account = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(accountNo + "Account Not Found"));
        Account receivingAccount = accountRepository.findByAccountNo(accountNo)
                .orElseThrow(() -> new AccountNotFoundException(receivingAccountNo + "Account Not Found"));
        account.transfer(receivingAccount, balance);
        accountRepository.saveAll(List.of(account, receivingAccount));
    }
}
