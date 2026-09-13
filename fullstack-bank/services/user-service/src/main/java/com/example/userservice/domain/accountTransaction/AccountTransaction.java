package com.example.userservice.domain.accountTransaction;

import com.example.userservice.domain.userAccount.UserAccount;
import com.example.userservice.domain.accountTransaction.enums.AccountTransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account_transaction")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sponsor_id", nullable = false)
    private UserAccount sponsor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserAccount recipient;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTransactionType type;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "creation_ts", nullable = false)
    private OffsetDateTime creationTs;

//    private String referenceNumber;

    private String referenceNumber = "MIKU-" + UUID.randomUUID().toString().substring(0,12).toUpperCase();
}