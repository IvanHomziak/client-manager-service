package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.ClientRequestDTO;
import com.ihomziak.webbankingapp.dto.ClientResponseDTO;
import com.ihomziak.webbankingapp.dto.ClientsInfoDTO;
import com.ihomziak.webbankingapp.entity.Client;
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
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        try {
            this.clientService.save(clientRequestDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (ClientException ex) {
            return ResponseEntity.ofNullable(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/clients/{uuid}")
    public Optional<ClientResponseDTO> getClient(@PathVariable String uuid) {
        return this.clientService.findByUUID(uuid);
    }

    @GetMapping("/clients")
    public List<ClientsInfoDTO> getClients() {
        return this.clientService.findAll();
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        this.clientService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/clients/update")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody @Valid Client client) {

        try {
            this.clientService.update(client);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (ClientException ex) {
            return ResponseEntity.ofNullable(HttpStatus.NOT_FOUND);
        }
    }

//    private Client convertToClient(ClientResponseDTO clientResponseDTO) {
//        return this.modelMapper.map(clientResponseDTO, Client.class);
//    }
//
//    private ClientResponseDTO convertToClientDTO(Client client) {
//        return this.modelMapper.map(client, ClientResponseDTO.class);
//    }
}
