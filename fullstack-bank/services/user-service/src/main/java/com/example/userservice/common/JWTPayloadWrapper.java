package com.example.userservice.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Builder
public class JWTPayloadWrapper {
    private String token;
    private Date issuedAt;
    private Date expirationTime;
}
