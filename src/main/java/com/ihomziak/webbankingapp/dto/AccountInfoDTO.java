package com.ihomziak.webbankingapp.dto;

import com.ihomziak.webbankingapp.enums.AccountType;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class AccountInfoDTO {

    private String accountNumber;
    private AccountType accountType;
    private long balance;
    private String UUID;
}
