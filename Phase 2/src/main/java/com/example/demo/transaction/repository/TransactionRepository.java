package com.example.demo.transaction.repository;

import com.example.demo.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT s FROM Transaction s WHERE s.userId =:userId")
    List<Transaction> findByUserId(Long userId);
}
