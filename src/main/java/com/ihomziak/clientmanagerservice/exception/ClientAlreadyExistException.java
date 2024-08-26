package com.ihomziak.clientmanagerservice.exception;

public class ClientAlreadyExistException extends RuntimeException {
    public ClientAlreadyExistException(String msg) {
        super(msg);
    }
}
