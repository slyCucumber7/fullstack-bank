package com.example.userservice.entity.userAccount;

import com.example.userservice.entity.bankUser.BankUser;
import com.example.userservice.entity.userAccount.Enums.AccountType;
import com.example.userservice.entity.userAccount.Enums.UserAccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private BankUser bankuser;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserAccountStatus status;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "creation_ts", nullable = false)
    private OffsetDateTime creationTs;
}