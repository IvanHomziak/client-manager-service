package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Optional<List<ClientsInfoDTO>> findAll();

    Optional<ClientResponseDTO> save(ClientRequestDTO client);

    Optional<ClientResponseDTO> deleteByUUID(String uuid);

    Optional<ClientResponseDTO> update(ClientRequestDTO clientRequestDTO);

    Optional<ClientResponseDTO> findClientByUUID(String uuid);
}
