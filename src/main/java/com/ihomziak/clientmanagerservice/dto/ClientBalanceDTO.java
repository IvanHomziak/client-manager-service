package com.ihomziak.clientmanagerservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBalanceDTO {

    private String clientUuid;
    private double balance;
}
