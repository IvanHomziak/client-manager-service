package com.ihomziak.webbankingapp.enums;

public enum AccountType {

    CHECKING("Checking"), SAVINGS("Savings"), CREDIT("Credit");

    private final String accountType;

    private AccountType(String accountType){
        this.accountType = accountType;
    }

    public String getAccountType() {
        return this.accountType;
    }

    @Override
    public String toString() {
        return getAccountType();
    }
}
