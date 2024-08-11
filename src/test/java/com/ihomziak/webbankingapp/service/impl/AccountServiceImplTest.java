package com.ihomziak.webbankingapp.service.impl;

import com.ihomziak.webbankingapp.dao.AccountRepository;
import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.AccountHolderDTO;
import com.ihomziak.webbankingapp.dto.AccountRequestDTO;
import com.ihomziak.webbankingapp.dto.AccountResponseDTO;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.enums.AccountType;
import com.ihomziak.webbankingapp.errors.GlobalExceptionHandler;
import com.ihomziak.webbankingapp.mapper.MapStructMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.ihomziak.webbankingapp.dao.AccountRepository;
import com.ihomziak.webbankingapp.dao.ClientRepository;
import com.ihomziak.webbankingapp.dto.*;
import com.ihomziak.webbankingapp.entity.Account;
import com.ihomziak.webbankingapp.entity.Client;
import com.ihomziak.webbankingapp.exception.AccountNotFoundException;
import com.ihomziak.webbankingapp.exception.ClientNotFoundException;
import com.ihomziak.webbankingapp.mapper.MapStructMapper;
import com.ihomziak.webbankingapp.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MapStructMapper mapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCheckingAccount_Success() {
        // Arrange
        String clientUUID = "client-uuid";
        AccountRequestDTO requestDTO = new AccountRequestDTO();
        requestDTO.setClientUUID(clientUUID);
        requestDTO.setAccountNumber("123456");

        Client client = new Client();
        Account account = new Account();
        AccountResponseDTO responseDTO = new AccountResponseDTO();

        when(clientRepository.findClientByUUID(clientUUID)).thenReturn(Optional.of(client));

        when(mapper.accountRequestDtoToAccount(requestDTO)).thenReturn(account);

//        when(accountRepository.findAccountByAccountNumber(anyString())).thenReturn(new ArrayList<>());
//        when(mapper.accountToAccountResponseDto(account)).thenReturn(responseDTO);
//        when(mapper.clientToAccountHolderDto(client)).thenReturn(new AccountHolderDTO());

        // Act
        AccountResponseDTO result = accountService.createCheckingAccount(requestDTO);

        // Assert
        assertNotNull(result);
        verify(accountRepository).save(account);
        verify(mapper).accountToAccountResponseDto(account);
    }
}