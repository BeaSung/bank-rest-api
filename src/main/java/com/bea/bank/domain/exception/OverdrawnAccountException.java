package com.bea.bank.domain.exception;

public class OverdrawnAccountException extends IllegalArgumentException {
    public OverdrawnAccountException(String message) {
        super(message);
    }
}
