package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.mapper.MapStructMapperImpl;
import com.ihomziak.webbankingapp.util.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public LinkedList<ClientsInfoDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        return this.mapper.clientsToClientInfoDTO(clients);
    }

    @Override
    public void save(ClientRequestDTO clientRequestDTO) {
        Client theClient = mapper.clientRequestDTOToClient(clientRequestDTO);
        theClient.setClientId(0);
        if (this.clientRepository.findClientByTaxNumber(theClient.getTaxNumber()).isPresent()) {
            throw new ClientException("Client already exist");
        } else {
            theClient.setUUID(UUID.randomUUID().toString());
            this.clientRepository.save(theClient);
        }
    }

    @Override
    public void deleteByUUID(String uuid) {
        Optional<Client> result = this.clientRepository.findClientByUUID(uuid);

        Client theClient;
        if (result.isPresent()) {
            theClient = result.get();
            this.clientRepository.delete(theClient);
        } else {
            throw new ClientException("Client not exist. UUID: " + uuid);
        }
    }

    @Override
    public Client update(Client client) {
        Optional<Client> theClient = clientRepository.findById(client.getClientId());

        if (theClient.isEmpty()) {
            throw new ClientException("Client is not exist");
        }

        Client newClient = new Client();

        newClient.setClientId(client.getClientId());
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setDateOfBirth(client.getDateOfBirth());
        newClient.setPhoneNumber(client.getPhoneNumber());
        newClient.setEmail(client.getEmail());
        newClient.setAddress(client.getAddress());


        return clientRepository.save(newClient);
    }

    @Override
    public Optional<ClientResponseDTO> findByUUID(String uuid) {
        Optional<Client> theClient = this.clientRepository.findClientByUUID(uuid);
        return Optional.ofNullable(this.mapper.clientToClientResponseDTO(theClient));
    }

}
