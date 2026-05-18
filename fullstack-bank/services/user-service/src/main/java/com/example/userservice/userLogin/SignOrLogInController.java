package com.example.userservice.userLogin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.common.ResponseWrapper;
import com.example.userservice.common.exception.FailedLoginException;
import com.example.userservice.common.exception.NotFoundException;
import com.example.userservice.entity.UserLogin;
import lombok.AllArgsConstructor;
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

    // This would hash the password then save it to the database
    @PostMapping("/signup")
    public ResponseEntity<ResponseWrapper<UserLogin>> signup (@RequestBody SignUpOrLogInRequest request) {
        UserLogin userLogin = userLoginService.findById(request.userId());

        if (userLogin == null) {
            throw new NotFoundException("User could not be found.");
        }

        userLoginService.hashPassword(request.userId(), request.password());

        return ResponseEntity.ok(ResponseWrapper.<UserLogin>builder()
                .status("200")
                .message("success")
                .description("Password was hashed and stored")
                .build());
    }

    @PostMapping("/login")
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

            String jwtToken = JWT.create()
                    .withIssuer("Miku Bank Inc.")
                    .withSubject("User details")
                    .withClaim("UserId", userLogin.getId())
                    .withIssuedAt(Date.from(now))
                    .withExpiresAt(Date.from(expirationTime))
                    .sign(algo);

            JwtResponse jwtResponse = JwtResponse.builder()
                    .token(jwtToken)
                    .id(userLogin.getId())
                    .build();

            ResponseWrapper<JwtResponse> ApiResponse = ResponseWrapper.<JwtResponse>builder().content(jwtResponse).build();

            return ResponseEntity.ok(ApiResponse);
        } else {
           throw new FailedLoginException("Invalid login credentials.");
        }
    }

}
