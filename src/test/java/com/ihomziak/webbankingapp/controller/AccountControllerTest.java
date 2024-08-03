package com.ihomziak.webbankingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ihomziak.webbankingapp.dto.*;
import com.ihomziak.webbankingapp.enums.AccountType;
import com.ihomziak.webbankingapp.errors.GlobalExceptionHandler;
import com.ihomziak.webbankingapp.exception.ClientNotFoundException;
import com.ihomziak.webbankingapp.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;
    @Mock
    private GlobalExceptionHandler exceptionHandler;

    @InjectMocks
    private AccountController clientController;

    private MockMvc mockMvc;

    private LocalDateTime localDate;
    private ObjectMapper objectMapper;
    private AccountRequestDTO accountRequestDTO;
    private AccountResponseDTO accountResponseDTO;
    private AccountHolderDTO accountHolder;
    private AccountInfoDTO accountInfoDTO;
    private String clientUuid;
    private String accountNumber;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Ensure this is added
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        clientUuid = "206625ce-3ee7-4174-8f92-4bdc41c18274";
        accountNumber = "165445000023211234";

        accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setAccountNumber(accountNumber);
        accountInfoDTO.setAccountType(AccountType.CHECKING);
        accountInfoDTO.setBalance(1000);
        accountInfoDTO.setUUID(clientUuid);

        accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setAccountNumber(accountNumber);
        accountRequestDTO.setAccountType(AccountType.CHECKING);
        accountRequestDTO.setBalance(1000);
        accountRequestDTO.setClientUUID("206625ce-3ee7-4174-8f92-4bdc41c18274");

        accountHolder = new AccountHolderDTO();
        accountHolder.setFirstName("John");
        accountHolder.setLastName("Doe");
        accountHolder.setUUID(clientUuid);

        accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setAccountId(1);
        accountResponseDTO.setAccountHolderDTO(accountHolder);
        accountResponseDTO.setAccountNumber(accountRequestDTO.getAccountNumber());
        accountResponseDTO.setAccountType(accountRequestDTO.getAccountType());
        accountResponseDTO.setBalance(accountRequestDTO.getBalance());
        accountResponseDTO.setUUID(clientUuid);
        accountResponseDTO.setCreatedAt(null);
        accountResponseDTO.setLastUpdated(null);
    }

    @Test
    public void getAccount_ShouldReturnAccount_WhenAccountExists() throws Exception {

        when(accountService.findAccountByUuid(clientUuid)).thenReturn(accountInfoDTO);

        mockMvc.perform(get("/api/account/{uuid}", clientUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().json(objectMapper.writeValueAsString(accountInfoDTO)));
    }

    @Test
    public void getAccount_ShouldReturnNotFound_WhenAccountDoesNotExist() throws Exception {
        String uuid = "non-existent-uuid";

        when(accountService.findAccountByUuid(uuid)).thenThrow(new ClientNotFoundException("Account not exist. UUID: " + uuid));

        mockMvc.perform(get("/api/account/{uuid}", uuid).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"errors\":[\"Account not exist. UUID: non-existent-uuid\"]}"));
    }
}
