package com.example.demo.providers.Services;

import com.example.demo.discount.service.DiscountService;
import com.example.demo.providers.models.Provider;
import com.example.demo.providers.repo.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProvidersServices {
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private DiscountService discountService;

    public ResponseEntity<Void> addProvider(Provider provider) {
        if (providerRepository.findByProviderName(provider.getProviderName()) == null) {
            providerRepository.save(provider);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    public Provider getProviderByName(String name) {
        return providerRepository.findByProviderName(name);
    }

    public List<Provider> getProvidersByServicesName(String name) {
        return providerRepository.findByServiceName(name);
    }

    public List<Provider> getAll() {
        return providerRepository.findAll();
    }


    public Map<String, Double> calculateInvoice(String providerName, Double amount) {
        Map<String, Double> map = new HashMap<>();

        Provider provider = providerRepository.findByProviderName(providerName);

        // Calculate the invoice
        amount += provider.getServicesPrice();
        List<Double> discounts = discountService.getServicesDiscounts(provider.getServicesName());
        for (Double discount : discounts) amount *= (1 - discount / 100.0);

        map.put("amount", Math.round(amount * 100) / 100.0);
        return map;
    }

    public Map<String, Double> vodaRechargeHandler(String mobileNumber, Double amount) {
        return calculateInvoice("vodafoneMob", amount);
    }

    public Map<String, Double> etisalatRechargeHandler(String mobileNumber, Double amount) {
        return calculateInvoice("etisalatMob", amount);
    }

    public Map<String, Double> orangeRechargeHandler(String mobileNumber, Double amount) {
        return calculateInvoice("orangeMob", amount);
    }

    public Map<String, Double> weMobRechargeHandler(String mobileNumber, Double amount) {
        return calculateInvoice("weMob", amount);
    }


    public Map<String, Double> vodaInternetHandler(String mobileNumber, Double amount) {
        return calculateInvoice("vodafoneInternet", amount);
    }

    public Map<String, Double> etisalatInternetHandler(String mobileNumber, Double amount) {
        return calculateInvoice("etisalatInternet", amount);
    }

    public Map<String, Double> orangeInternetHandler(String mobileNumber, Double amount) {
        return calculateInvoice("orangeInternet", amount);
    }

    public Map<String, Double> weInternetHandler(String mobileNumber, Double amount) {
        return calculateInvoice("weInternet", amount);
    }

    public Map<String, Double> NGOsDonHandler(String charityName, Double amount) {
        return calculateInvoice("NGOs", amount);
    }

    public Map<String, Double> schoolsHandler(String schoolName, Double amount) {
        return calculateInvoice("school", amount);
    }

    public Map<String, Double> cancerHospitalDonHandler(String hospitalName, Double amount) {
        return calculateInvoice("cancer", amount);
    }

    public Map<String, Double> monthlyLandLineHandler(String mobileNumber, Double amount) {
        return calculateInvoice("monthlyReceipt", amount);
    }

    public Map<String, Double> quarterLandLineHandler(String mobileNumber, Double amount) {
        return calculateInvoice("quarterReceipt", amount);

    }
}
