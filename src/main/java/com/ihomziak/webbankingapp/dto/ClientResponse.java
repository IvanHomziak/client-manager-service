package com.ihomziak.webbankingapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientResponse {

    private List<ClientDTO> clients;

    public ClientResponse(List<ClientDTO> clients) {
        this.clients = clients;
    }
}
