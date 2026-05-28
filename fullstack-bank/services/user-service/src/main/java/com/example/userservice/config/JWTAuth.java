package com.example.userservice.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

@Component
public class JWTAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = "Authorization";
        String authenticationScheme = "Bearer";

        final String authHeader = request.getHeader(authenticationHeader);

        String jwt = "";

        if (authHeader != null && authHeader.startsWith(authenticationScheme)) {
            jwt = authHeader.substring(authenticationScheme.length() + 1);
        }

        try {
            DecodedJWT decodedJWT = JWT.decode(jwt);

            if (decodedJWT.getExpiresAt().toInstant().isBefore(Instant.now())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("The token: " + jwt + " expired.");

                return;
            } else {
                Long userID = decodedJWT.getClaim("UserId").asLong();
                UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(userID, null, new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }

        } catch (JWTDecodeException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }
}
