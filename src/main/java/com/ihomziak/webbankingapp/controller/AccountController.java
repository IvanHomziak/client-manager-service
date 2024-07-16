package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.service.AccountService;
import com.ihomziak.webbankingapp.util.AccountException;
import com.ihomziak.webbankingapp.util.ClientException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

//    @PostMapping("/account")
//    public ResponseEntity<AccountResponseDTO> addAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
//        this.accountService.save(accountRequestDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(accountRequestDTO);
//    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable long id) {
        this.accountService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
