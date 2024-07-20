package com.ihomziak.webbankingapp.service;

import com.ihomziak.webbankingapp.dao.AccountRepository;
import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.AccountHolderDTO;
import com.ihomziak.webbankingapp.dto.AccountInfoDTO;
import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.exception.AccountNotFoundException;
import com.ihomziak.webbankingapp.exception.ClientNotFoundException;
import com.ihomziak.webbankingapp.mapper.MapStructMapper;
import com.ihomziak.webbankingapp.util.AccountNumberGenerator;
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
    public AccountResponseDTO createCheckingAccount(AccountRequestDTO accountRequestDTO) {
        Optional<Client> client = this.clientRepository.findClientByUUID(accountRequestDTO.getClientUUID());

        if (client.isEmpty()) {
            throw new ClientNotFoundException("Client not found");
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

        return accountResponseDTO;
    }

    @Override
    public AccountInfoDTO deleteAccount(String uuid) {
        Optional<Account> account = this.accountRepository.findAccountByUUID(uuid);

        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account not exist: " + uuid);
        }
        this.accountRepository.delete(account.get());
        return mapper.accountToAccountInfoDto(account.get());
    }

    @Override
    public AccountResponseDTO updateAccount(AccountRequestDTO accountRequestDTO) {
        Optional<Account> account = this.accountRepository.findAccountByUUID(accountRequestDTO.getClientUUID());
        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account number " + accountRequestDTO.getAccountNumber() + " not exist: " + accountRequestDTO.getClientUUID());
        }

        Account theAccount = account.get();
        Optional<Client> theClient = this.clientRepository.findClientByUUID(accountRequestDTO.getClientUUID());
        if (theClient.isEmpty()) {
            throw new ClientNotFoundException("Client not found");
        }

        theAccount.setClient(theClient.get());
        theAccount.setAccountNumber(accountRequestDTO.getAccountNumber());
        theAccount.setAccountType(accountRequestDTO.getAccountType());
        theAccount.setBalance(accountRequestDTO.getBalance());
        theAccount.setLastUpdate(LocalDateTime.now());

        this.accountRepository.save(theAccount);
        return mapper.accountToAccountResponseDto(theAccount);
    }

    @Override
    public List<AccountInfoDTO> findAllAccounts() {
        List<Account> accountList = this.accountRepository.findAll();
        if (accountList.isEmpty()) {
            throw new AccountNotFoundException("Accounts not found exception");
        }
        List<AccountInfoDTO> accountInfoDTOList = new ArrayList<>();
        for (Account account : accountList) {
            accountInfoDTOList.add(mapper.accountToAccountInfoDto(account));
        }
        return accountInfoDTOList;
    }

    @Override
    public AccountInfoDTO findAccountByUuid(String uuid) {
        Optional<Account> account = this.accountRepository.findAccountByUUID(uuid);
        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account not exist. UUID: " + uuid);
        }
        return mapper.accountToAccountInfoDto(account.get());
    }
}
