package com.example.bankapp.exception;

public class CurrencyCheckException extends RuntimeException {
    public CurrencyCheckException(String message) {
        super(message);
    }
}
