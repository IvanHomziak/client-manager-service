package com.ihomziak.webbankingapp.controller;

import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.service.AccountService;
import com.ihomziak.webbankingapp.util.AccountException;
import com.ihomziak.webbankingapp.util.ClientException;
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

    @GetMapping("/account")
    public List<Account> getAccounts() {
        return this.accountService.findAll();
    }

    @PostMapping("/account")
    public ResponseEntity<HttpStatus> addAccount(@RequestBody @Valid Account account) {
        account.setAccountId(0);

        try {
            if (this.accountService.findAccountByNumber(account.getAccountNumber()).isPresent()) {
                throw new AccountException("Account already exist");
            } else {
                this.accountService.save(account);
                return ResponseEntity.ok(HttpStatus.OK);
            }
        } catch (ClientException ex) {
            return ResponseEntity.ofNullable(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable long id) {
        this.accountService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
