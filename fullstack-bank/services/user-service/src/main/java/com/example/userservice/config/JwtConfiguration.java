package com.example.userservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-issuer}")
    private String jwtIssuer;

    @Bean
    public JWTVerifier configureJwtVerifier() {
        /*
            TODO: determine desired JWTVerifier config, test functionality
         */
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .withIssuer(jwtIssuer)
                .build();
    }

}
