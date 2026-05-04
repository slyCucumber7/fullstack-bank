package com.example.userservice.BankUser;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "The bank user attributes to be updated, and their new values. Attributes with null values will not be updated."
)
public class BankUserRequest {

    @Size(max = 30)
    String nameF;

    @Size(max = 30)
    String nameM;

    @Size(max = 60)
    String nameL;

    @Size(max = 32)
    String addrStreet;

    @Size(max = 32)
    String addrCity;

    @Size(max = 2)
    String addrSt;

    @Size(max = 9)
    String addrZip;

    @Size(max = 15)
    String phone;

    @Size(max = 100)
    String email;

    @Size(max = 32)
    String password;
}
