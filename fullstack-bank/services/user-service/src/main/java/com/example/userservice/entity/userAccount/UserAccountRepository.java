package com.example.userservice.entity.userAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
