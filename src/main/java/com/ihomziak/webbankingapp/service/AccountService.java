package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findById(long id);

    List<Account> findAll();

    Optional<Account> findAccountByNumber(String accountNumber);

    void save(Account account);

    void deleteById(long accountId);
}
