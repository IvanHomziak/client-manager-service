package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.enums.AccountType;
import com.ihomziak.webbankingapp.mapper.MapStructMapperImpl;
import com.ihomziak.webbankingapp.util.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MapStructMapperImpl mapper;


    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository,
                             MapStructMapperImpl mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ClientsInfoDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return this.mapper.clientsToClientInfoDto(clients);
    }

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) {
        if (this.clientRepository.findClientByTaxNumber(clientRequestDTO.getTaxNumber()).isPresent()) {
            throw new ClientException("Client already exist");
        }

        Client theClient = mapper.clientRequestDtoToClient(clientRequestDTO);

        theClient.setUUID(UUID.randomUUID().toString());

        Account account = new Account();
        account.setAccountType(AccountType.CHECKING);
        account.setClient(theClient);

        List<Account> accounts = Collections.singletonList(account);
        theClient.setAccount(accounts);
        theClient.setCreatedAt(LocalDateTime.now());
        this.clientRepository.save(theClient);

//        Optional<Client> client = Optional.of(theClient);
        return mapper.clientToClientResponseDto(theClient);
    }

    @Override
    public ClientResponseDTO deleteByUUID(String uuid) {
        Optional<Client> client = this.clientRepository.findClientByUUID(uuid);

        if (client.isEmpty()) {
            throw new ClientException("Client does not exist");
        }

        this.clientRepository.delete(client.get());

        return mapper.clientToClientResponseDto(client.get());
    }

    @Override
    public ClientResponseDTO update(ClientRequestDTO clientRequestDTO) {
        Optional<Client> theClient = clientRepository.findClientByTaxNumber(clientRequestDTO.getTaxNumber());

        if (theClient.isEmpty()) {
            throw new ClientException("Client is not exist");
        }

        Client newClient = theClient.get();

        newClient.setFirstName(clientRequestDTO.getFirstName());
        newClient.setLastName(clientRequestDTO.getLastName());
        newClient.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        newClient.setEmail(clientRequestDTO.getEmail());
        newClient.setAddress(clientRequestDTO.getAddress());

        clientRepository.save(newClient);

        return mapper.clientToClientResponseDto(newClient);
    }

    @Override
    public ClientResponseDTO findClientByUUID(String uuid) {
        Optional<Client> theClient = this.clientRepository.findClientByUUID(uuid);

        if (theClient.isEmpty()) {
            throw new ClientException("Client not exist. UUID: " + uuid);
        }

        return this.mapper.clientToClientResponseDto(theClient.get());
    }
}
