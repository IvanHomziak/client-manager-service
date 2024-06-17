package com.ihomziak.webbankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientDTO {

    private long clientId;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String email;

    private String phoneNumber;

    private String address;
}
