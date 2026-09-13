package com.example.userservice.domain.auth;

import lombok.Data;

import java.util.List;

@Data
public class BankUserDetails {

    List<String> roles;
    Long id;
    String name;

}
