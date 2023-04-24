package com.example.demo.discount.repository;

import com.example.demo.discount.model.Discount;
import com.example.demo.user.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Query(value = "SELECT s.percentage FROM Discount s WHERE UPPER(s.servicesName) = UPPER(:servicesName) OR UPPER(s.servicesName) = 'ALL'")
    List<Double> findServicesDiscounts(String servicesName);
}
