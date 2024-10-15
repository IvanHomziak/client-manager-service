package com.ihomziak.clientmanagerservice.dto;

import lombok.*;

@Getter
@Setter
public class TransactionRequestDTO {

    private Integer transactionEventId;
    private String senderUuid;
    private String receiverUuid;
    private Double amount;
    private String transactionStatus;
}