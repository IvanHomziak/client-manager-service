package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.AccountRepository;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.util.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(long id) {
        return this.accountRepository.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> findAccountByNumber(long accountNumber) {
        return Optional.ofNullable(this.accountRepository.findAccountByAccountNumber(accountNumber));
    }

    @Override
    public void save(Account account) {
        this.accountRepository.save(account);
    }

    @Override
    public void deleteById(long accountId) {
        Optional<Account> result = this.accountRepository.findById(accountId);

        Account theAccount;
        if (result.isPresent()) {
            theAccount = result.get();
            this.accountRepository.delete(theAccount);
        } else {
            throw new ClientException("Account not exist: " + accountId);
        }
    }
}
