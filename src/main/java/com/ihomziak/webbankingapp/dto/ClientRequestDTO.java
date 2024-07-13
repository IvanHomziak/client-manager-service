package com.ihomziak.webbankingapp.dto;

import lombok.Setter;
import lombok.Getter;

import java.sql.Timestamp;

@Setter
@Getter
public class ClientRequestDTO {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String taxNumber;
    private String email;
    private String phoneNumber;
    private String address;
}
