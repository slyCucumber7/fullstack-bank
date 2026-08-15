package com.example.userservice.entity.accountTransaction;


import com.example.userservice.entity.accountTransaction.enums.AccountTransactionStatus;
import com.example.userservice.entity.accountTransaction.enums.AccountTransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionResponse {

    private String transactionId;
    private Long sponsorAccountId;
    private Long recipientAccountId;
    private BigDecimal amount;
    private AccountTransactionType type;
    private AccountTransactionStatus status;
    private OffsetDateTime timestamp;
}
