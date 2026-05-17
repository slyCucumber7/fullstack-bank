package com.example.userservice.BankUser;

import com.example.userservice.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    @Query(
        """
        SELECT NEW com.example.userservice.BankUser.BankUserDto(u)
        FROM BankUser u
        WHERE u.id = :id
        """)
    public BankUserDto getUserById(Long id);

    public BankUser findBankUserById(Long id);
}
