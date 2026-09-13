package com.example.userservice.config;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JWTVerifier verifier;

    /**
     * Validates and decodes JWT tokens
     *
     * @param token
     * JWT token to be validated
     * @return
     * Decoded JWT token
     */
    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }


}
