package com.ihomziak.clientmanagerservice.dto;

import com.ihomziak.clientmanagerservice.util.AccountType;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class AccountInfoDTO {

    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private String UUID;
}
