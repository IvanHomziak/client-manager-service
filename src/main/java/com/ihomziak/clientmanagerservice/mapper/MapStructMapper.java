package com.ihomziak.clientmanagerservice.mapper;

import com.ihomziak.clientmanagerservice.dto.*;
import com.ihomziak.clientmanagerservice.entity.Account;
import com.ihomziak.clientmanagerservice.entity.Client;

import java.util.List;
import java.util.Optional;

public interface MapStructMapper {

    Client clientRequestDtoToClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO clientToClientResponseDto(Client theClient);

    List<ClientsInfoDTO> clientsToClientInfoDto(List<Client> clients);

    AccountResponseDTO accountToAccountResponseDto(Account account);

    AccountHolderDTO clientToAccountHolderDto(Optional<Client> theClient);

    Account accountRequestDtoToAccount(AccountRequestDTO accountRequestDTO);

    AccountInfoDTO accountToAccountInfoDto(Account accountByUUID);
}

