package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.service.ClientService;
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
        return ResponseEntity.status(HttpStatus.FOUND).body(this.clientService.findClientByUUID(uuid));
    }

    @GetMapping("/clients")
    public ResponseEntity<Optional<List<ClientsInfoDTO>>> getClients() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.clientService.findAll());
    }

    @DeleteMapping("/clients/{uuid}")
    public ResponseEntity<Optional<ClientResponseDTO>> deleteClient(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.deleteByUUID(uuid));
    }

    @PatchMapping("/clients/update")
    public ResponseEntity<Optional<ClientResponseDTO>> updateClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.clientService.update(clientRequestDTO));
    }
}
