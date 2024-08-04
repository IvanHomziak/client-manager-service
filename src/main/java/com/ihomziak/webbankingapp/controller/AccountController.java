package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.AccountInfoDTO;
import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;
import com.ihomziak.webbankingapp.service.AccountService;
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
