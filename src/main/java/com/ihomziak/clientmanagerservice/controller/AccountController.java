package com.ihomziak.clientmanagerservice.controller;

import com.ihomziak.clientmanagerservice.service.AccountService;
import com.ihomziak.clientmanagerservice.dto.AccountInfoDTO;
import com.ihomziak.clientmanagerservice.dto.AccountRequestDTO;
import com.ihomziak.clientmanagerservice.dto.AccountResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{uuid}")
    public ResponseEntity<AccountInfoDTO> getAccount(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountService.findAccountByUuid(uuid));
    }

    @GetMapping("/account/list/{uuid}")
    public ResponseEntity<List<AccountResponseDTO>> getClientAccounts(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountService.findAllAccountsByClientUUID(uuid));
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDTO> createCheckingAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.createCheckingAccount(accountRequestDTO));
    }

    @DeleteMapping("/account/{uuid}")
    public ResponseEntity<AccountInfoDTO> deleteAccount(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.accountService.deleteAccount(uuid));
    }

    @PatchMapping("/account")
    public ResponseEntity<AccountResponseDTO> updateAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.accountService.updateAccount(accountRequestDTO));
    }

    @GetMapping("/account")
    public ResponseEntity<List<AccountInfoDTO>> getAccounts() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountService.findAllAccounts());
    }

}
