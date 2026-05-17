package com.example.userservice.UserLogin;

import com.example.userservice.BankUser.BankUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_login")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {

    @Id
    private Integer id;

    // Copies the primary key ID in BankUser
    @OneToOne
    @JoinColumn(name = "id") // Matches the 'id' column name in the database
    private BankUser bankUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "hash", nullable = false, length = 255)
    private String hash;
}
