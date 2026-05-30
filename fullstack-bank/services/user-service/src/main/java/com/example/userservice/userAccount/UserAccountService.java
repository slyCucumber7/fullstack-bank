package com.example.userservice.userAccount;


import com.example.userservice.BankUser.BankUserRepository;
import com.example.userservice.entity.BankUser;
import com.example.userservice.entity.UserAccount;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service

public class UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Transactional
    public void createUserAccount(BankUser user) {
        UserAccount checkingUserAccount = UserAccount.builder()
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.CHECKING)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.from(Instant.now()))
                .build();

         userAccountRepository.save(checkingUserAccount);

        UserAccount savingUserAccount = UserAccount.builder()
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.SAVINGS)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.from(Instant.now()))
                .build();

        userAccountRepository.save(savingUserAccount);

        UserAccount invesmentUserAccount = UserAccount.builder()
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.INVESTMENT)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.from(Instant.now()))
                .build();

        userAccountRepository.save(invesmentUserAccount);

    }
}
