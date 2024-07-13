package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.entity.Client;
import org.springframework.stereotype.Component;

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
}
