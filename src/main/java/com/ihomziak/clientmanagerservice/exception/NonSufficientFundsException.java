package com.ihomziak.clientmanagerservice.exception;

public class NonSufficientFundsException extends RuntimeException {

    public NonSufficientFundsException(String message) {
        super(message);
    }
}