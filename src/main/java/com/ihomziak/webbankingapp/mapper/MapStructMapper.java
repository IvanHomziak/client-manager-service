package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface MapStructMapper {

    Client clientRequestDTOToClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO clientToClientResponseDTO(Optional<Client> theClient);

    LinkedList<ClientsInfoDTO> clientsToClientInfoDTO(List<Client> clients);
}

