package com.ihomziak.webbankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private long clientId;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "date_of_birth")
    @NotNull
    private String dateOfBirth;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "created_at")
    private Timestamp createdAt;

//    @NotNull
    @OneToMany(mappedBy = "client")
    private List<Account> account;
}
