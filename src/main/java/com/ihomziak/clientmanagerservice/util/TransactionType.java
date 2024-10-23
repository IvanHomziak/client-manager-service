package com.ihomziak.clientmanagerservice.util;

public enum TransactionType {

    TRANSFER("TRANSFER"),
    DEPOSIT("DEPOSIT"),
    WITHDRAW("WITHDRAW");

    private final String name;

    private TransactionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}