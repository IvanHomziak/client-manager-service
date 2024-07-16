package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dto.AccountInfoDTO;
import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<AccountInfoDTO> findAccountByUuid(String accountNumber);

    Optional<AccountResponseDTO> createCheckingAccount(AccountRequestDTO account);

    Optional<AccountInfoDTO> deleteAccount(String uuid);

    Optional<AccountResponseDTO> updateAccount(AccountRequestDTO accountRequestDTO);

    List<Optional<AccountInfoDTO>> findAllAccounts();
}
