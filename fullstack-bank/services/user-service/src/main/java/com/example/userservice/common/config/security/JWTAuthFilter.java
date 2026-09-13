package com.example.userservice.common.config.security;


import com.example.userservice.domain.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends PathExclusionFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("\n========================================");
        System.out.println("REQUEST TO CONTEXT PATH: " + request.getRequestURI() + " INTERCEPTED BY JWT AUTH FILTER");

        Cookie[] cookies = request.getCookies();

        String jwt ="";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("   -> Found Cookie Name: [" + cookie.getName() + "] | Value: [" + cookie.getValue().substring(0, Math.min(10, cookie.getValue().length())) + "...]");
                if (cookie.getName().equals("jwt-token")) {
                    jwt = cookie.getValue();
                    System.out.println("SUCCESS: Found 'jwt-token' match");
                    break;
                }
            }
        }

        System.out.println(jwt != null && !jwt.isBlank() ? "JWT:\n" + jwt : "BLANK JWT RECEIVED");
        var decodedJWT = jwtService.validateToken(jwt);


        Long userID = decodedJWT.getClaim("UserId").asLong();
        UsernamePasswordAuthenticationToken authenticated = new UsernamePasswordAuthenticationToken(
                userID,
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        filterChain.doFilter(request, response);
    }
}
