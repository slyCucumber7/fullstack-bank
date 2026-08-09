package com.example.userservice.entity.accountTransaction;

import com.example.userservice.common.exception.InsufficientFundsException;
import com.example.userservice.entity.BankUser;
import com.example.userservice.entity.UserAccount;
import com.example.userservice.entity.userAccount.Enums.AccountType;
import com.example.userservice.entity.userAccount.Enums.UserAccountStatus;
import com.example.userservice.entity.userAccount.UserAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import com.example.userservice.entity.bankUser.BankUserService;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
@Service
@AllArgsConstructor
public class AccountTransactionService {
    private final AccountTransactionRepository accountTransactionRepository;
    private final BankUserService bankUserService;
    private final UserAccountRepository UserAccountService;

    /*  This is our main method, every other way of making a transaction (e.g. email or phone will be
        called in this method for safety */

    private void transferAmountById(Long sponsorAccountId, Long recipientAccountId, BigDecimal amount) {
        UserAccount sponsorAccount = UserAccountService.findById(sponsorAccountId).orElseThrow(EntityNotFoundException::new);
        UserAccount recipientAccount = UserAccountService.findById(recipientAccountId).orElseThrow(EntityNotFoundException::new);


        if (sponsorAccount == null || recipientAccount == null) {
            throw new EntityNotFoundException("Sponsor User: " + sponsorAccountId + "& Recipient User: " +  recipientAccountId + "not found");
        }

        if (sponsorAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Not enough funds");
        }

        if (sponsorAccount.getStatus() != UserAccountStatus.ACTIVE ||
                recipientAccount.getStatus() != UserAccountStatus.ACTIVE) {
            throw new IllegalStateException("One or both accounts are not active");
        }

        sponsorAccount.setBalance(sponsorAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));


    }

    // OTHER TRANSACTION METHODS

    private void transferAmountByEmail(Long sponsorID, String recipientEmail, BigDecimal amount) {
        BankUser recipientUser = bankUserService.getUserByEmail(recipientEmail);

        if (recipientUser == null) {
            throw new EntityNotFoundException("User " + recipientEmail + " not found");
        }

        Long recipientId = recipientUser.getId();

        transferAmountById(sponsorID, recipientId, amount);
    }

    private void transferAmountbyPhone(Long sponsorAccountId, String number, BigDecimal amount) {
        BankUser recipientUser = bankUserService.getUserByPhone(number);

        if (recipientUser == null) {
            throw new EntityNotFoundException("User with phone " + number + " not found");
        }

        UserAccount recipientWallet = recipientUser.getUserAccounts().stream()
                        .filter(userAccount -> userAccount.getType() == AccountType.CHECKING
                            && userAccount.getStatus() == UserAccountStatus.ACTIVE)
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("User with phone " + number + " not found"));

        Long recipientWalletId = recipientWallet.getId();

        transferAmountById(sponsorAccountId, recipientWalletId, amount);

    }


}
