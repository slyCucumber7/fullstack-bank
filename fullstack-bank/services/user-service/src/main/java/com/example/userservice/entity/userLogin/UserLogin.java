package com.example.userservice.entity.userLogin;

import com.example.userservice.entity.bankUser.BankUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "user_login")
@NoArgsConstructor
public class UserLogin {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private BankUser bankUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "hash", nullable = false)
    private String hash;

}