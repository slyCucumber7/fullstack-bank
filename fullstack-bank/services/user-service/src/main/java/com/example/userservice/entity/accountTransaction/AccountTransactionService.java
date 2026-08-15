package com.example.userservice.entity.accountTransaction;

import com.example.userservice.common.exception.InsufficientFundsException;
import com.example.userservice.entity.BankUser;
import com.example.userservice.entity.UserAccount;
import com.example.userservice.entity.accountTransaction.enums.AccountTransactionStatus;
import com.example.userservice.entity.accountTransaction.enums.AccountTransactionType;
import com.example.userservice.entity.userAccount.Enums.AccountType;
import com.example.userservice.entity.userAccount.Enums.UserAccountStatus;
import com.example.userservice.entity.userAccount.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.userservice.entity.bankUser.BankUserService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;
    private final BankUserService bankUserService;
    private final UserAccountRepository UserAccountService;

    /*  This is our main method, every other way of making a transaction (e.g. email or phone will be
        called in this method for safety */

    private AccountTransactionResponse transferAmountById(Long sponsorAccountId, Long recipientAccountId, BigDecimal amount, AccountTransactionType type) {

        UserAccount sponsorAccount = UserAccountService.findById(sponsorAccountId).orElseThrow(EntityNotFoundException::new);
        UserAccount recipientAccount = UserAccountService.findById(recipientAccountId).orElseThrow(EntityNotFoundException::new);

        if (sponsorAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Not enough funds");
        }

        if (sponsorAccount.getStatus() != UserAccountStatus.ACTIVE ||
                recipientAccount.getStatus() != UserAccountStatus.ACTIVE) {
            throw new IllegalStateException("One or both accounts are not active");
        }

        sponsorAccount.setBalance(sponsorAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

        String referenceNumber = "MIKU-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();

        return AccountTransactionResponse.builder()
                .transactionId(referenceNumber)
                .sponsorAccountId(sponsorAccountId)
                .amount(amount)
                .recipientAccountId(recipientAccountId)
                .type(type)
                .timestamp(OffsetDateTime.now())
                .status(AccountTransactionStatus.COMPLETE)
                .build();

    }

    // Main public method for handling the incoming transaction from AccountTransactionService

    @Transactional
    public AccountTransactionResponse transactionProcess(AccountTransactionRequest request) {
        Long recipientAccountId;

        if (request.getRecipientAccountId() != null) {
            recipientAccountId = request.getRecipientAccountId();
        } else if (request.getRecipientEmail() != null && !request.getRecipientEmail().isBlank()) {
            recipientAccountId = getRecipientAccountIdByEmail(request.getRecipientEmail(), request.getAccountType());
        } else if (request.getRecipientNumber() != null && !request.getRecipientNumber().isBlank()) {
            recipientAccountId = getRecipientAccountIdByPhone(request.getRecipientNumber(), request.getAccountType());
        } else {
            throw new IllegalArgumentException("A recipient account ID, email, or phone number must be provided.");
        }

        return transferAmountById(request.getSponsorId(), recipientAccountId, request.getAmount(), request.getType());
    }

    private Long getRecipientAccountIdByEmail(String recipientEmail, AccountType targetType) {
        BankUser user = bankUserService.getUserByEmail(recipientEmail);

        if (user == null) {
            throw new EntityNotFoundException("Recipient user with email " + recipientEmail + " not found");
        }

        AccountType selectedType = (targetType != null) ? targetType : AccountType.CHECKING;

        UserAccount userAccountType = user.getUserAccounts().stream()
                .filter(acc -> acc.getType() == selectedType && acc.getStatus() == UserAccountStatus.ACTIVE)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Sponsor User: " + recipientEmail + "not found"));


        return userAccountType.getId();
    }

    private Long getRecipientAccountIdByPhone(String recipientPhone, AccountType targetType) {
        BankUser user = bankUserService.getUserByPhone(recipientPhone);

        if (user == null) {
            throw new EntityNotFoundException("Recipient user with phone " + recipientPhone + " not found");
        }

        AccountType selectedType = (targetType != null) ? targetType : AccountType.CHECKING;

        UserAccount userAccountType = user.getUserAccounts().stream()
                .filter(acc -> acc.getType() == selectedType && acc.getStatus() == UserAccountStatus.ACTIVE)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Sponsor User: " + recipientPhone + "not found"));


        return userAccountType.getId();
    }

}
