package com.ihomziak.clientmanagerservice.service;

import com.ihomziak.clientmanagerservice.dto.ClientRequestDTO;
import com.ihomziak.clientmanagerservice.dto.ClientResponseDTO;
import com.ihomziak.clientmanagerservice.dto.ClientsInfoDTO;

import java.util.List;

public interface ClientService {

    List<ClientsInfoDTO> findAll();

    ClientResponseDTO save(ClientRequestDTO client);

    ClientResponseDTO deleteByUUID(String uuid);

    ClientResponseDTO update(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO findClientByUUID(String uuid);
}