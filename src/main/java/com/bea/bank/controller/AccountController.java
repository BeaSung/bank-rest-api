package com.bea.bank.controller;

import com.bea.bank.controller.request.CreateAccountRequest;
import com.bea.bank.controller.request.DepositMoneyRequest;
import com.bea.bank.controller.request.TransferMoneyRequest;
import com.bea.bank.controller.request.WithdrawMoneyRequest;
import com.bea.bank.controller.response.AccountResponse;
import com.bea.bank.domain.Account;
import com.bea.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void createAccount(@RequestBody CreateAccountRequest request) {
        accountService.createAccount(request.getName());
    }

    @GetMapping("/{accountNo}")
    public AccountResponse findAccount(@PathVariable String accountNo) {
        Account account = accountService.findAccount(accountNo);
        return new AccountResponse(account);
    }

    @DeleteMapping("/{accountNo}")
    public void deleteAccount(@PathVariable String accountNo) {
        accountService.deleteAccount(accountNo);
    }

    @PatchMapping("/{accountNo}")
    public void deposit(@PathVariable String accountNo, @RequestBody DepositMoneyRequest request) {
        accountService.deposit(accountNo, request.getMoney());
    }

    @PatchMapping("/{accountNo}")
    public void withdraw(@PathVariable String accountNo, @RequestBody WithdrawMoneyRequest request) {
        accountService.withdraw(accountNo, request.getMoney());
    }

    @PatchMapping("/{accountNo}")
    public void transfer(@PathVariable String accountNo, @RequestBody TransferMoneyRequest request) {
        accountService.transfer(accountNo, request.getReceivingAccountNo(), request.getMoney());
    }
}
