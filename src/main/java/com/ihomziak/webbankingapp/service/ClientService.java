package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findAll();

    Optional<Client> findById(Long id);

    Optional<Client> findClientByEmail(Client client);

    void save(Client client);

    void deleteById(Long clientId);

    Client update(Client client);
}
