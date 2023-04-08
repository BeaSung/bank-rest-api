package com.bea.bank.controller.response;

import com.bea.bank.domain.Account;
import com.bea.bank.domain.TransactionHistory;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponse {

    private String accountNo;
    private String name;
    private Long balance;
    private List<TransactionHistory> transactionHistories;

    public AccountResponse(Account account) {
        this.accountNo = account.getAccountNo();
        this.name = account.getName();
        this.balance = account.getBalance();
        this.transactionHistories = account.getTransactionHistories();
    }
}
