package com.example.userservice.entity.userLogin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.userservice.common.JWTPayloadWrapper;
import com.example.userservice.common.JwtResponse;
import com.example.userservice.common.ResponseWrapper;
import com.example.userservice.common.exception.FailedLoginException;
import com.example.userservice.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("user-login/")
public class SignOrLogInController {

    private final UserLoginService userLoginService;

    @PostMapping("login")
    public ResponseEntity<ResponseWrapper<JwtResponse>> login (@RequestBody SignUpOrLogInRequest request) {

        UserLogin userLogin = userLoginService.findById(request.userId());
        if (userLogin == null) {
            throw new NotFoundException("User could not be found.");
        }

        boolean verify = userLoginService.verifyPassword(request.password(), userLogin);

        if (verify) {
            // JWT Token Generator using Auth0 JWT
            Algorithm algo = Algorithm.HMAC256("mikubeam");

            JWTVerifier token = JWT.require(algo)
                    .withIssuer("Miku Bank Inc.")
                    .build();

            Instant now = Instant.now();
            Instant expirationTime = now.plusSeconds(6700);

            /*
                TODO: add user claim, register it as role in security context, secure endpoints
             */

            String jwtToken = JWT.create()
                    .withIssuer("Miku Bank Inc.")
                    .withSubject("User details")
                    .withClaim("UserId", userLogin.getId())
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(expirationTime))
                    .sign(algo);


            ResponseCookie cookie = ResponseCookie.from("jwt-token", jwtToken)
                    .maxAge(3600)
                    .httpOnly(true)
                    .secure(true)
                    .build();


            DecodedJWT decodedJWT = JWT.decode(cookie.getValue());

            Date issuedAt = decodedJWT.getIssuedAt();
            Date expiresAt = decodedJWT.getExpiresAt();

            JWTPayloadWrapper payloadWrapper = JWTPayloadWrapper.builder()
                    .token(cookie.getValue())
                    .issuedAt(issuedAt)
                    .expirationTime(expiresAt)
                    .build();

            JwtResponse response = JwtResponse.builder()
                    .id(userLogin.getId())
                    .payloadWrapper(payloadWrapper)
                    .build();

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(ResponseWrapper.<JwtResponse>builder()
                            .status("200")
                            .message("User credential verified, generating token...")
                            .content(response)
                            .build());

        } else {
           throw new FailedLoginException("Invalid login credentials.");
        }
    }

}
