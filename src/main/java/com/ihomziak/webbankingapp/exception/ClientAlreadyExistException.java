package com.ihomziak.webbankingapp.exception;

public class ClientAlreadyExistException extends RuntimeException {
    public ClientAlreadyExistException(String msg) {
        super(msg);
    }
}
