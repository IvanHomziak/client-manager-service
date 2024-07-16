package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.*;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public Client clientRequestDtoToClient(ClientRequestDTO clientRequestDTO) {

        if (clientRequestDTO == null) {
            return null;
        }

        Client client = new Client();

        client.setFirstName(clientRequestDTO.getFirstName());
        client.setLastName(clientRequestDTO.getLastName());
        client.setTaxNumber(clientRequestDTO.getTaxNumber());
        client.setAddress(clientRequestDTO.getAddress());
        client.setEmail(clientRequestDTO.getEmail());
        client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        client.setDateOfBirth(clientRequestDTO.getDateOfBirth());

        return client;
    }

    @Override
    public ClientResponseDTO clientToClientResponseDto(Optional<Client> theClient) {
        Client client = theClient.get();
        ClientResponseDTO responseDTO = new ClientResponseDTO();

        responseDTO.setClientId(client.getClientId());
        responseDTO.setFirstName(client.getFirstName());
        responseDTO.setLastName(client.getLastName());
        responseDTO.setTaxNumber(client.getTaxNumber());
        responseDTO.setAddress(client.getAddress());
        responseDTO.setEmail(client.getEmail());
        responseDTO.setPhoneNumber(client.getPhoneNumber());
        responseDTO.setDateOfBirth(client.getDateOfBirth());
        responseDTO.setUUID(client.getUUID());
        responseDTO.setCreatedAt(client.getCreatedAt());
        responseDTO.setUpdateAt(client.getUpdatedAt());

        return responseDTO;
    }

    @Override
    public List<ClientsInfoDTO> clientsToClientInfoDto(List<Client> clients) {

        ClientsInfoDTO clientInfoDTO = new ClientsInfoDTO();
        List<ClientsInfoDTO> clientsInfoDTOList = new ArrayList<>();

        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);

            clientInfoDTO.setFirstName(client.getFirstName());
            clientInfoDTO.setLastName(client.getLastName());
            clientInfoDTO.setEmail(client.getEmail());
            clientInfoDTO.setPhoneNumber(client.getPhoneNumber());

            clientsInfoDTOList.add(clientInfoDTO);
        }

        return clientsInfoDTOList;
    }

    @Override
    public AccountResponseDTO accountToAccountResponseDto(Account account) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO();


        accountHolderDTO.setFirstName(account.getClient().getFirstName());
        accountHolderDTO.setLastName(account.getClient().getLastName());
        accountHolderDTO.setUUID(account.getClient().getUUID());

        accountResponseDTO.setAccountId(account.getAccountId());
        accountResponseDTO.setAccountHolderDTO(accountHolderDTO);
        accountResponseDTO.setBalance(account.getBalance());
        accountResponseDTO.setAccountNumber(account.getAccountNumber());
        accountResponseDTO.setAccountType(account.getAccountType());
        accountResponseDTO.setUUID(account.getUUID());
        accountResponseDTO.setCreatedAt(account.getCreatedAt());
        accountResponseDTO.setLastUpdated(account.getLastUpdate());

        return accountResponseDTO;
    }

    @Override
    public AccountHolderDTO clientToAccountHolderDto(Optional<Client> theClient) {
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO();
        Client client = theClient.get();

        accountHolderDTO.setFirstName(client.getFirstName());
        accountHolderDTO.setLastName(client.getLastName());
        accountHolderDTO.setUUID(client.getUUID());

        return accountHolderDTO;
    }

    @Override
    public Account accountRequestDtoToAccount(AccountRequestDTO accountRequestDTO) {

        Account account = new Account();

        account.setAccountNumber(accountRequestDTO.getAccountNumber());
        account.setAccountType(accountRequestDTO.getAccountType());
        account.setBalance(accountRequestDTO.getBalance());
        account.setUUID(accountRequestDTO.getClientUUID());

        return account;
    }

    @Override
    public AccountInfoDTO accountToAccountInfoDto(Account accountByUUID) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setAccountType(accountByUUID.getAccountType());
        accountInfoDTO.setAccountNumber(accountByUUID.getAccountNumber());
        accountInfoDTO.setBalance(accountByUUID.getBalance());
        accountInfoDTO.setUUID(accountByUUID.getUUID());
        return accountInfoDTO;
    }
}
