package com.example.userservice.entity.accountTransaction;


import com.example.userservice.entity.accountTransaction.enums.AccountTransactionType;
import com.example.userservice.entity.userAccount.Enums.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountTransactionRequest {

    @NotNull(message = "Sponsor ID is required")
    private Long sponsorId;

    // Optional destination identifiers (Service picks whichever is present)
    private String recipientEmail;
    private String recipientNumber;
    private Long recipientAccountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Transaction type is required")
    private AccountTransactionType type;

    // Optional: Defaults to CHECKING in service if left null
    private AccountType accountType;
}
