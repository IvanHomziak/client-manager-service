package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public Client clientRequestDTOToClient(ClientRequestDTO clientRequestDTO) {

        if (clientRequestDTO == null) {
            return null;
        }

        Client client = new Client();

        client.setFirstName(clientRequestDTO.getFirstName());
        client.setLastName(clientRequestDTO.getLastName());
        client.setTaxNumber(clientRequestDTO.getTaxNumber());
        client.setAddress(clientRequestDTO.getAddress());
        client.setEmail(clientRequestDTO.getEmail());
        client.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        client.setDateOfBirth(clientRequestDTO.getDateOfBirth());

        return client;
    }

    @Override
    public ClientResponseDTO clientToClientResponseDTO(Optional<Client> theClient) {
        Client client = theClient.get();
        ClientResponseDTO responseDTO = new ClientResponseDTO();

        responseDTO.setFirstName(client.getFirstName());
        responseDTO.setLastName(client.getLastName());
        responseDTO.setTaxNumber(client.getTaxNumber());
        responseDTO.setAddress(client.getAddress());
        responseDTO.setEmail(client.getEmail());
        responseDTO.setPhoneNumber(client.getPhoneNumber());
        responseDTO.setDateOfBirth(client.getDateOfBirth());
        responseDTO.setUUID(client.getUUID());
        responseDTO.setCreatedAt(client.getCreatedAt());
        responseDTO.setUpdateAt(client.getUpdatedAt());

        return responseDTO;
    }

    @Override
    public LinkedList<ClientsInfoDTO> clientsToClientInfoDTO(List<Client> clients) {

        ClientsInfoDTO clientInfoDTO = new ClientsInfoDTO();
        LinkedList<ClientsInfoDTO> clientsInfoDTOList = new LinkedList<>();

        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);

            clientInfoDTO.setFirstName(client.getFirstName());
            clientInfoDTO.setLastName(client.getLastName());
            clientInfoDTO.setEmail(client.getEmail());
            clientInfoDTO.setPhoneNumber(client.getPhoneNumber());

            clientsInfoDTOList.add(clientInfoDTO);
        }

        return clientsInfoDTOList;
    }
}
