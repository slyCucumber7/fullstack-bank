package com.example.userservice.BankUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;
import org.hibernate.boot.jaxb.mapping.GenerationTiming;
import org.hibernate.generator.EventType;

import java.time.OffsetDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "bank_user")
@DynamicInsert
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "name_f", nullable = false, length = 30)
    private String nameF;

    @Size(max = 60)
    @NotNull
    @Column(name = "name_l", nullable = false, length = 60)
    private String nameL;

    @Size(max = 30)
    @NotNull
    @Column(name = "name_m", nullable = true, length = 30)
    private String nameM;

    @Size(max = 32)
    @NotNull
    @Column(name = "addr_street", nullable = false, length = 32)
    private String addrStreet;

    @Size(max = 32)
    @NotNull
    @Column(name = "addr_city", nullable = false, length = 32)
    private String addrCity;

    @Size(max = 2)
    @NotNull
    @Column(name = "addr_st", nullable = false, length = 2)
    private String addrSt;

    @Size(max = 9)
    @NotNull
    @Column(name = "addr_zip", nullable = false, length = 9)
    private String addrZip;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

//    @NotNull
    @ColumnDefault("now()")
    @Column(name = "creation_ts", nullable = false)
    @Generated(event = EventType.INSERT)
    private OffsetDateTime creationTs;

}