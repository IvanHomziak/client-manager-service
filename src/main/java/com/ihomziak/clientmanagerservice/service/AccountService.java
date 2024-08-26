package com.ihomziak.clientmanagerservice.service;

import com.ihomziak.clientmanagerservice.dto.AccountInfoDTO;
import com.ihomziak.clientmanagerservice.dto.AccountRequestDTO;
import com.ihomziak.clientmanagerservice.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    AccountInfoDTO findAccountByUuid(String accountNumber);

    AccountResponseDTO createCheckingAccount(AccountRequestDTO account);

    AccountInfoDTO deleteAccount(String uuid);

    AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO);

    List<AccountInfoDTO> findAllAccounts();

    List<AccountResponseDTO> findAllAccountsByClientUUID(String uuid);
}
