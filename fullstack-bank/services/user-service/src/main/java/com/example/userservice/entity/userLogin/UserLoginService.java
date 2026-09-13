package com.example.userservice.entity.userLogin;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginService {
    private PasswordEncoder passwordEncoder;
    private UserLoginRepository userLoginRepository;

    public void saveUserLogin(UserLogin userLogin) {
        userLoginRepository.save(userLogin);
    }

    public UserLogin findById(Long id) {
        return userLoginRepository.findById(id).orElse(null);
    }

    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean verifyPassword(String rawPassword, UserLogin userLogin) {
        return passwordEncoder.matches(rawPassword, userLogin.getHash());
    }
}
