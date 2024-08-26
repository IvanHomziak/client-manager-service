package com.ihomziak.clientmanagerservice.dto;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String UUID;
}
