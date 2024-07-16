package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.ClientAccountsDTO;
import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.service.ClientService;
import com.ihomziak.webbankingapp.util.ClientException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;

    }

    @PostMapping("/clients")
    public ResponseEntity<Optional<ClientResponseDTO>> addClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(clientRequestDTO));
    }

    @GetMapping("/clients/{uuid}")
    public ResponseEntity<Optional<ClientResponseDTO>> getClient(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.clientService.findByUUID(uuid));
    }

    @GetMapping("/clients")
    public ResponseEntity<Optional<List<ClientsInfoDTO>>> getClients() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.clientService.findAll());
    }

    @DeleteMapping("/clients/{uuid}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable String uuid) {
        this.clientService.deleteByUUID(uuid);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/clients/update")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {

        try {
            this.clientService.update(clientRequestDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (ClientException ex) {
            return ResponseEntity.ofNullable(HttpStatus.NOT_FOUND);
        }
    }
}
