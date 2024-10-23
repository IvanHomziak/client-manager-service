package com.ihomziak.clientmanagerservice.dto;

import com.ihomziak.clientmanagerservice.util.TransactionStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponseDTO {

    private Integer transactionEventId;
    private String transactionUuid;
    private TransactionStatus transactionStatus;
    private String errorMessage;
    private Double updatedSenderBalance;
    private Double updatedReceiverBalance;
}