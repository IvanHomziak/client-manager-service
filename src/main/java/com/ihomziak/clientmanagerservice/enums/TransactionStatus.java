package com.ihomziak.clientmanagerservice.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    NEW("NEW"),
    CREATED("CREATED"),
    STARTED("STARTED"),
    COMPLETED("COMPLETED"),
    DECLINED("DECLINED"),
    CANCELED("CANCELED");


    private final String value;

    private TransactionStatus(String value) {
        this.value = value;
    }
}