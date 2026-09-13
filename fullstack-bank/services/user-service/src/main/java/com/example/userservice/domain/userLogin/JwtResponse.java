package com.example.userservice.domain.userLogin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {
        private Long id;
        private JWTPayloadWrapper payloadWrapper;
}
