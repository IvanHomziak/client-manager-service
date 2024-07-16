package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.AccountRepository;
import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.AccountHolderDTO;
import com.ihomziak.webbankingapp.dto.AccountInfoDTO;
import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.mapper.MapStructMapper;
import com.ihomziak.webbankingapp.util.AccountException;
import com.ihomziak.webbankingapp.util.AccountNumberGenerator;
import com.ihomziak.webbankingapp.util.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final MapStructMapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository, MapStructMapper mapper) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AccountResponseDTO> createCheckingAccount(AccountRequestDTO accountRequestDTO) {
        Optional<Client> client = this.clientRepository.findClientByUUID(accountRequestDTO.getClientUUID());

        if (client.isEmpty()) {
            throw new ClientException("Client not found");
        }

        Optional<Account> accountInDB = this.accountRepository.findAccountByAccountNumber(accountRequestDTO.getAccountNumber()).stream().findAny();
        Account theAccount = mapper.accountRequestDtoToAccount(accountRequestDTO);

        while (accountInDB.stream().anyMatch(accountNumber -> accountNumber.getAccountNumber().equals(theAccount.getAccountNumber()))) {
            theAccount.setAccountNumber(AccountNumberGenerator.generateBankAccountNumber());
        }
        theAccount.setClient(client.get());
        theAccount.setCreatedAt(LocalDateTime.now());

        this.accountRepository.save(theAccount);

        AccountResponseDTO accountResponseDTO = mapper.accountToAccountResponseDto(theAccount);
        AccountHolderDTO accountHolderDTO = mapper.clientToAccountHolderDto(client);

        accountResponseDTO.setAccountHolderDTO(accountHolderDTO);

        return Optional.of(accountResponseDTO);
    }

    @Override
    public Optional<AccountInfoDTO> deleteAccount(String uuid) {
        Optional<Account> account = this.accountRepository.findAccountByUUID(uuid);

        if (account.isEmpty()) {
            throw new AccountException("Account not exist: " + uuid);
        }
        this.accountRepository.delete(account.get());
        return Optional.of(mapper.accountToAccountInfoDto(account.get()));
    }

    @Override
    public Optional<AccountResponseDTO> updateAccount(AccountRequestDTO accountRequestDTO) {
        Optional<Account> account = this.accountRepository.findAccountByAccountNumber(accountRequestDTO.getAccountNumber());
        if (account.isEmpty()) {
            throw new AccountException("Account number " + accountRequestDTO.getAccountNumber() + " not exist: " + accountRequestDTO.getClientUUID());
        }

        Account theAccount = mapper.accountRequestDtoToAccount(accountRequestDTO);
        Optional<Client> theClient = this.clientRepository.findClientByUUID(accountRequestDTO.getClientUUID());
        if (theClient.isEmpty()) {
            throw new ClientException("Client not found");
        }
        theAccount.setClient(theClient.get());
        theAccount.setLastUpdate(LocalDateTime.now());
        this.accountRepository.save(theAccount);
        return Optional.of(mapper.accountToAccountResponseDto(theAccount));
    }

    @Override
    public List<Optional<AccountInfoDTO>> findAllAccounts() {
        List<Account> accountList = this.accountRepository.findAll();
        List<Optional<AccountInfoDTO>> accountInfoDTOList = new ArrayList<>();
        for (Account account : accountList) {
            accountInfoDTOList.add(Optional.of(mapper.accountToAccountInfoDto(account)));
        }
        return accountInfoDTOList;
    }

    @Override
    public Optional<AccountInfoDTO> findAccountByUuid(String uuid) {
        Optional<Account> account = this.accountRepository.findAccountByUUID(uuid);
        if (account.isEmpty()) {
            throw new AccountException("Account not exist: " + uuid);
        }
        return Optional.of(mapper.accountToAccountInfoDto(account.get()));
    }
}
