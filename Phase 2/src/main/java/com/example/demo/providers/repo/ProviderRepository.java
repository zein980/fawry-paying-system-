package com.example.demo.providers.repo;

import com.example.demo.providers.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Query("SELECT s FROM Provider s WHERE UPPER(s.providerName) = UPPER(:providerName) ")
    Provider findByProviderName(String providerName);
    @Query("SELECT s FROM Provider s WHERE UPPER(s.servicesName) = UPPER(:servicesName) ")
    List<Provider> findByServiceName(String servicesName);
}
