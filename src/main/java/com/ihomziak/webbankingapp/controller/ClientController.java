package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.ClientDTO;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.service.ClientService;
import com.ihomziak.webbankingapp.util.ClientException;
import com.ihomziak.webbankingapp.util.ClientValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
    private final ClientValidator validator;
    private ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService,
                            ClientValidator validator) {
        this.clientService = clientService;
        this.validator = validator;
        this.modelMapper = new ModelMapper();

    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return this.clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        Optional<Client> theClient = clientService.findById(id);
        if (theClient.isEmpty()) {
            throw new RuntimeException("Client id not found - " + id);
        }

        return theClient;
    }

    @PostMapping("/clients")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid Client client) {
        client.setClientId(0);

        try {
            if (this.clientService.findClientByEmail(client).isPresent()) {
                throw new ClientException("Client already exist");
            } else {
                this.clientService.save(client);
                return ResponseEntity.ok(HttpStatus.OK);
            }
        } catch (ClientException ex) {
            return ResponseEntity.ofNullable(HttpStatus.CONFLICT);
        }
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

    private Client convertToClient(ClientDTO clientDTO) {
        clientDTO.setClientId(0);
        return this.modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertToClientDTO(Client client) {
        return this.modelMapper.map(client, ClientDTO.class);
    }
}
