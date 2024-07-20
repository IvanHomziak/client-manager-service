package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;

import java.util.List;

public interface ClientService {

    List<ClientsInfoDTO> findAll();

    ClientResponseDTO save(ClientRequestDTO client);

    ClientResponseDTO deleteByUUID(String uuid);

    ClientResponseDTO update(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO findClientByUUID(String uuid);
}
