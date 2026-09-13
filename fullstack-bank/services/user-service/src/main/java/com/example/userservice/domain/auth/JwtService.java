package com.example.userservice.domain.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JWTVerifier verifier;

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-issuer}")
    private String jwtIssuer;

    @Value("${jwt-expiration-milis}")
    private String expirationMiliseconds;

    /**
     * Validates and decodes JWT auth tokens
     *
     * @param token
     * JWT token to be validated
     * @return
     * Decoded JWT token
     */
    public DecodedJWT validateToken(String token) {
        return verifier.verify(token);
    }

    public List<String> getRoles(DecodedJWT decodedJWT){
        return decodedJWT.getClaim("roles").asList(String.class);
    }

    /**
     * Creates a JWT auth token from user details
     *
     * @param bankUserDetails User Details
     * @return
     * JWT auth token
     *
     */
    public String createToken(BankUserDetails bankUserDetails){
        return JWT.create()
                .withIssuer(jwtIssuer)
                    .withSubject(bankUserDetails.getName())
                .withClaim("UserId", bankUserDetails.getId())
                .withClaim("roles", bankUserDetails.getRoles())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusMillis(Long.parseLong(expirationMiliseconds))))
                .sign(Algorithm.HMAC256(jwtSecret));
    }


}
