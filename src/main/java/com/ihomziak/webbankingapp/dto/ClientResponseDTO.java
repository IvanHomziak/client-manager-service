package com.ihomziak.webbankingapp.dto;

import lombok.Setter;
import lombok.Getter;

import java.sql.Timestamp;

@Setter
@Getter
public class ClientResponseDTO {

    private long clientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String taxNumber;
    private String email;
    private String phoneNumber;
    private String address;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private String  UUID;
}
