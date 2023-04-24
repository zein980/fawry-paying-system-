package com.example.demo.user.repository;

import com.example.demo.user.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    @Query("SELECT s FROM Account s WHERE s.email = :email")
    Optional<Account> findByEmail(String email);
    @Query("SELECT s FROM Account s WHERE s.email = :email and s.password = :password")
    Optional<Account> findByEmailPass(String email, String password);
}
