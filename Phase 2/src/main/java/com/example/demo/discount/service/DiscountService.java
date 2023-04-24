package com.example.demo.discount.service;

import com.example.demo.discount.model.Discount;
import com.example.demo.discount.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public void addDiscount(Discount discount) {
        discountRepository.save(discount);
    }

    public List<Double> getServicesDiscounts(String servicesName) {
        return discountRepository.findServicesDiscounts(servicesName);
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }
}
