package com.ihomziak.webbankingapp.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String msg) {
        super(msg);
    }
}
