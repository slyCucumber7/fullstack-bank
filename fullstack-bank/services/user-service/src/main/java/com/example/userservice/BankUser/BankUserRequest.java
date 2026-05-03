package com.example.userservice.BankUser;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BankUserRequest {
    @NotNull
    @Size(max = 30)
    String nameF;

    @Size(max = 30)
    String nameM;

    @NotNull
    @Size(max = 60)
    String nameL;

    @NotNull
    @Size(max = 32)
    String addrStreet;

    @NotNull
    @Size(max = 32)
    String addrCity;

    @NotNull
    @Size(max = 2)
    String addrSt;

    @NotNull
    @Size(max = 9)
    String addrZip;

    @NotNull
    @Size(max = 15)
    String phone;

    @NotNull
    @Size(max = 100)
    String email;

    @NotNull
    @Size(max = 32)
    String password;
}
