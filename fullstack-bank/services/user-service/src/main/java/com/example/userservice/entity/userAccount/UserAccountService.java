package com.example.userservice.entity.userAccount;


import com.example.userservice.entity.bankUser.BankUser;
import com.example.userservice.entity.userAccount.Enums.AccountType;
import com.example.userservice.entity.userAccount.Enums.UserAccountStatus;
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
                .id(null)
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.CHECKING)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.ofInstant(Instant.now(), java.time.ZoneOffset.UTC))
                .build();

         userAccountRepository.save(checkingUserAccount);

        UserAccount savingUserAccount = UserAccount.builder()
                .id(null)
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.SAVINGS)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.ofInstant(Instant.now(), java.time.ZoneOffset.UTC))
                .build();

        userAccountRepository.save(savingUserAccount);

        UserAccount invesmentUserAccount = UserAccount.builder()
                .id(null)
                .name(user.getNameF() + " " + user.getNameL() + "'s Checking")
                .bankuser(user)
                .type(AccountType.INVESTMENT)
                .balance(BigDecimal.ZERO)
                .status(UserAccountStatus.ACTIVE)
                .creationTs(OffsetDateTime.ofInstant(Instant.now(), java.time.ZoneOffset.UTC))
                .build();

        userAccountRepository.save(invesmentUserAccount);

    }

    public UserAccount findById(Long id) { return userAccountRepository.findById(id).orElse(null); }
}
