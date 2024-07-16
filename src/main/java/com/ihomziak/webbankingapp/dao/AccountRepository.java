package com.ihomziak.webbankingapp.dao;

import com.ihomziak.webbankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByUUID(String uuid);

    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
