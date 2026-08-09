package com.example.userservice.entity.userLogin;

import com.example.userservice.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
}
