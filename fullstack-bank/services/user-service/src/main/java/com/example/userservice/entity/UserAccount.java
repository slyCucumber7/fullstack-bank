package com.example.userservice.entity;

import com.example.userservice.userAccount.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Size(max = 32)
    @NotNull
    @Column(name = "type", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotNull
    @ColumnDefault("0.00")
    @Column(name = "balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    @Size(max = 32)
    @NotNull
    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "creation_ts", nullable = false)
    private OffsetDateTime creationTs;

}