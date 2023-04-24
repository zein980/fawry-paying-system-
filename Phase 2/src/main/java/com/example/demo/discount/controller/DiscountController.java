package com.example.demo.discount.controller;

import com.example.demo.discount.model.Discount;
import com.example.demo.discount.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("discounts")

public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> addDiscount(@RequestBody Discount discount) {
        discountService.addDiscount(discount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/all")
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping(value = "search/service")
    public Map<String, Double> getServicesDiscounts(@RequestParam("serviceName") String serviceName) {
        Map<String, Double> map = new HashMap<>();
        List<Double> discounts = discountService.getServicesDiscounts(serviceName);
        for (int i = 0; i < discounts.size(); i++)
            map.put("Discount " + (i + 1), discounts.get(i));
        return map;
    }
}
