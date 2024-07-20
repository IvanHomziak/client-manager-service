package com.ihomziak.webbankingapp.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String msg) {
        super(msg);
    }
}
