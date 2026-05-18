package com.example.userservice.userLogin;

import com.example.userservice.BankUser.BankUserDto;
import com.example.userservice.entity.BankUser;
import com.example.userservice.entity.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLoginService {
    private PasswordEncoder passwordEncoder;
    private UserLoginRepository userLoginRepository;

    public UserLogin findById(Long id) {
        return userLoginRepository.findById(id).orElse(null);
    }

    public void hashPassword(Long id, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        UserLogin user = findById(id);

        user.setHash(hashedPassword);

        userLoginRepository.save(user);
    }

    public boolean verifyPassword(String rawPassword, UserLogin userLogin) {
        return passwordEncoder.matches(rawPassword, userLogin.getHash());
    }
}
