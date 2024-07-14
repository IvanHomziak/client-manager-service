package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientsInfoDTO> findAll();

    void save(ClientRequestDTO client);

    void deleteByUUID(String uuid);

    void update(ClientRequestDTO clientRequestDTO);

    Optional<ClientResponseDTO> findByUUID(String uuid);
}
