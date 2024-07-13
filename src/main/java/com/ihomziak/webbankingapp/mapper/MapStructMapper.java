package com.ihomziak.webbankingapp.mapper;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.entity.Client;

public interface MapStructMapper {

    Client clientRequestDTOToClient(ClientRequestDTO clientRequestDTO);
}
