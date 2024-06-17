package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.util.ClientException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public Optional<Client> findClientByEmail(Client client) {
        return this.clientRepository.findClientByEmail(client.getEmail());
    }

    @Override
    public void save(Client client) {
        this.clientRepository.save(client);
    }

    @Override
    public void deleteById(Long clientId) {
        Optional<Client> result = this.clientRepository.findById(clientId);

        Client theClient;
        if (result.isPresent()) {
            theClient = result.get();
            this.clientRepository.delete(theClient);
        } else {
            throw new ClientException("Client not exist: " + clientId);
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
}
