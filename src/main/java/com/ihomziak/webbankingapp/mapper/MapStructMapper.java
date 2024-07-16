package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.*;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface MapStructMapper {

    Client clientRequestDtoToClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO clientToClientResponseDto(Optional<Client> theClient);

    List<ClientsInfoDTO> clientsToClientInfoDto(List<Client> clients);

    AccountResponseDTO accountToAccountResponseDto(Account account);

    AccountHolderDTO clientToAccountHolderDto(Optional<Client> theClient);

    Account accountRequestDtoToAccount(AccountRequestDTO accountRequestDTO);

    AccountInfoDTO accountToAccountInfoDto(Account accountByUUID);
}

