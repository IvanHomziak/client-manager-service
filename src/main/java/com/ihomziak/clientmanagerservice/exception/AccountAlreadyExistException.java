package com.ihomziak.clientmanagerservice.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String msg) {
        super(msg);
    }
}
