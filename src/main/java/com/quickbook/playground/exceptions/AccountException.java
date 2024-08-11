package com.quickbook.playground.exceptions;

public class AccountException extends RuntimeException {
    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }
}
