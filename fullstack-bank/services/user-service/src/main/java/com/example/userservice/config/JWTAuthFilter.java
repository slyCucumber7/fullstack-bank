package com.example.userservice.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;

@Component
public class JWTAuthFilter extends PathExclusionFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("\n========================================");
        System.out.println("REQUEST TO CONTEXT PATH: " + request.getRequestURI() + " INTERCEPTED BY JWT AUTH FILTER");

        Cookie[] cookies = request.getCookies();

        String jwt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("   -> Found Cookie Name: [" + cookie.getName() + "] | Value: [" + cookie.getValue().substring(0, Math.min(10, cookie.getValue().length())) + "...]");
                if (cookie.getName().equals("jwt-token")) {
                    jwt =  cookie.getValue();
                    System.out.println("SUCCESS: Found 'jwt-token' match");
                    break;
                }
            }
        }

        
        try {
            DecodedJWT decodedJWT = JWT.decode(jwt);

            if (decodedJWT.getExpiresAt().toInstant().isBefore(Instant.now())) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("The token: " + jwt + " expired.");

                return;
            } else {

                Long userID = decodedJWT.getClaim("UserId").asLong();
                UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(
                        userID,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

                SecurityContextHolder.getContext().setAuthentication(authenticated);
            }

        } catch (JWTDecodeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("Invalid or malformed token format.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
