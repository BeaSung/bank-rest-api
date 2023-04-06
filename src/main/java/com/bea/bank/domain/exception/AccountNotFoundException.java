package com.bea.bank.domain.exception;

import com.bea.bank.domain.Account;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
