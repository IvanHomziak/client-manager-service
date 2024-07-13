package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientsInfoDTO> findAll();

    void save(ClientRequestDTO client);

    void deleteById(Long clientId);

    Client update(Client client);

    Optional<ClientResponseDTO> findByUUID(String uuid);
}
