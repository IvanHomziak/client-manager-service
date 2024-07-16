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
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{uuid}")
    public ResponseEntity<Optional<AccountInfoDTO>> getAccount(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountService.findAccountByUuid(uuid));
    }

    @PostMapping("/account")
    public ResponseEntity<Optional<AccountResponseDTO>> createCheckingAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.createCheckingAccount(accountRequestDTO));
    }

    @DeleteMapping("/account/{uuid}")
    public ResponseEntity<Optional<AccountInfoDTO>> deleteAccount(@PathVariable String uuid) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.accountService.deleteAccount(uuid));
    }

    @PatchMapping("/account")
    public ResponseEntity<Optional<AccountResponseDTO>> updateAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.accountService.updateAccount(accountRequestDTO));
    }

    @GetMapping("/account")
    public ResponseEntity<List<Optional<AccountInfoDTO>>> getAccount() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.accountService.findAllAccounts());
    }
}
