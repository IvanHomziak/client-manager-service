package com.ihomziak.webbankingapp.dao;

import com.ihomziak.webbankingapp.entity.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByEmail(@NotNull String email);

    Optional<Client> findClientByTaxNumber(String taxNumber);

    Optional<Client> findClientByUUID(@NotNull String uuid);
}
