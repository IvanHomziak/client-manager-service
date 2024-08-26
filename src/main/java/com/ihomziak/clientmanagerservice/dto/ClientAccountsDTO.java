package com.ihomziak.clientmanagerservice.dto;

import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Setter
@Getter
public class ClientAccountsDTO {

    private String firstName;
    private String lastName;
    private String uuID;
    private List<AccountInfoDTO> list;
}
