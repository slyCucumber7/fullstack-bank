package com.example.userservice.userLogin;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.userservice.common.ResponseWrapper;
import com.example.userservice.entity.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseWrapper.<UserLogin>builder()
                            .status("404")
                            .message("not found")
                            .description("Twin you inputted nothing, not found")
                            .build());
        }

        userLoginService.hashPassword(request.userId(), request.password());

        return ResponseEntity.ok(ResponseWrapper.<UserLogin>builder()
                .status("200")
                .message("success")
                .description("Password was hashed and stored")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<UserLogin>> login (@RequestBody SignUpOrLogInRequest request) {
        UserLogin userLogin = userLoginService.findById(request.userId());

        if (userLogin == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseWrapper.<UserLogin>builder());
        }

        boolean verify = userLoginService.verifyPassword(request.password(), userLogin);

        if (verify) {

            return ResponseEntity.ok(ResponseWrapper.<UserLogin>builder()
                    .status("200")
                    .message("User authenticated")
                    .description("User inputted correct password")
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseWrapper.<UserLogin>builder().status("401")
                    .message("User password input does not match")
                    .build());
        }
    }

}
