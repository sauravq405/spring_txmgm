package com.demo.tx.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
