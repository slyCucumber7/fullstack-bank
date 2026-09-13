package com.example.userservice.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JwtTestController {

    private final JwtService jwtService;

    @Value("${server.servlet.context-path}")
    private  String baseUrl;

    @PostMapping("/test/auth")
    public ResponseEntity<String> getAuth(@RequestBody BankUserDetails details){
        var token = jwtService.createToken(details);

        ResponseCookie cookie = ResponseCookie.from("jwt-token", token).path(baseUrl).build();
        System.out.println("PATH: " + cookie.getPath());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("TOKEN IN COOKIE HEADER");
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/auth/plest")
    public ResponseEntity<String> testAuth(@CookieValue(name="jwt-token") String token){

        return ResponseEntity.ok("TOKEN:\n" + token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/auth/plest1")
    public ResponseEntity<String> testRole(@CookieValue(name="jwt-token") String token){
        return ResponseEntity.ok("TOKEN:\n" + token);
    }





}
