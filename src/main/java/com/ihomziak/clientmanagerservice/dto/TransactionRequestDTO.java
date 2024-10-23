package com.ihomziak.clientmanagerservice.dto;

import com.ihomziak.clientmanagerservice.util.TransactionStatus;
import com.ihomziak.clientmanagerservice.util.TransactionType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRequestDTO {

    private Integer transactionEventId;
    private String transactionUuid;
    private String senderUuid;
    private String receiverUuid;
    private Double amount;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
}