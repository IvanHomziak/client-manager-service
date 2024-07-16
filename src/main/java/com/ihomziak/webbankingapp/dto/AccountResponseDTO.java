package com.ihomziak.webbankingapp.dto;

import com.ihomziak.webbankingapp.enums.AccountType;
import jakarta.persistence.Column;
import lombok.Setter;
import lombok.Getter;

import java.sql.Timestamp;

@Setter
@Getter
public class AccountResponseDTO {

    private int accountId;
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
    private AccountHolderDTO accountHolderDTO;
    private String UUID;
}
