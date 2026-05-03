package com.example.userservice.BankUser;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class BankUserDto implements Serializable {
    @NotNull
    Long id;
    @Size(max = 30)
    String nameF;
    @NotNull
    @Size(max = 60)
    String nameL;
    @NotNull
    @Size(max = 30)
    String nameM;
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
    OffsetDateTime creationTs;

    public BankUserDto(BankUser u){
       this.id = u.getId();
       this.nameF = u.getNameF();
       this.nameL = u.getNameL();
       this.nameM = u.getNameM();
       this.addrStreet = u.getAddrStreet();
       this.addrCity = u.getAddrCity();
       this.addrSt = u.getAddrSt();
       this.addrZip = u.getAddrZip();
       this.phone = u.getPhone();
       this.email = u.getEmail();
       this.creationTs = u.getCreationTs();
    }
}