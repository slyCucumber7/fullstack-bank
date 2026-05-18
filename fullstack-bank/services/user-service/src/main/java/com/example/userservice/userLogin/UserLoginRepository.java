package com.example.userservice.userLogin;

import com.example.userservice.BankUser.BankUserDto;
import com.example.userservice.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
}
