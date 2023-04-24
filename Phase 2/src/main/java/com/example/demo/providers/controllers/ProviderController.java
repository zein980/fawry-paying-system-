package com.example.demo.providers.controllers;

import com.example.demo.providers.Services.ProvidersServices;
import com.example.demo.providers.models.Provider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("providers")
public class ProviderController {
    private final ProvidersServices providersServices;

    public ProviderController(ProvidersServices providersServices) {
        this.providersServices = providersServices;
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> addProvider(@RequestBody Provider provider) {
        return providersServices.addProvider(provider);
    }

    @GetMapping(value = "/search/provider")
    public Provider getProviderByName(@RequestParam("name") String providerName) {
        return providersServices.getProviderByName(providerName);
    }

    @GetMapping(value = "/search/services")
    public List<Provider> getServicesByName(@RequestParam("name") String servicesName) {
        return providersServices.getProvidersByServicesName(servicesName);
    }

    @GetMapping(value = "/all")
    public List<Provider> getAllProviders() {
        return providersServices.getAll();
    }

    @GetMapping(value = "etisalatMob")
    public Map<String, Double> etisalatRechargeHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.etisalatRechargeHandler(mobileNumber, amount);
    }

    @GetMapping(value = "orangeMob")
    public Map<String, Double> orangeRechargeHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.orangeRechargeHandler(mobileNumber, amount);
    }

    @GetMapping(value = "weMob")
    public Map<String, Double> weMobRechargeHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.weMobRechargeHandler(mobileNumber, amount);
    }

    @GetMapping(value = "vodafoneMob")
    public Map<String, Double> vodaRechargeHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.vodaRechargeHandler(mobileNumber, amount);
    }

    @GetMapping(value = "vodafoneInternet")
    public Map<String, Double> vodaInternetHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.vodaInternetHandler(mobileNumber, amount);
    }

    @GetMapping(value = "etisalatInternet")
    public Map<String, Double> etisalatInternetHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.etisalatRechargeHandler(mobileNumber, amount);
    }

    @GetMapping(value = "orangeInternet")
    public Map<String, Double> orangeInternetHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.orangeInternetHandler(mobileNumber, amount);
    }

    @GetMapping(value = "weInternet")
    public Map<String, Double> weInternetHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.weInternetHandler(mobileNumber, amount);
    }

    @GetMapping(value = "NGOs")
    public Map<String, Double> NGOsDonHandler(@RequestParam("charityName") String charityName, @RequestParam("amount") Double amount) {
        return providersServices.NGOsDonHandler(charityName, amount);
    }

    @GetMapping(value = "school")
    public Map<String, Double> schoolsHandler(@RequestParam("schoolName") String schoolName, @RequestParam("amount") Double amount) {
        return providersServices.schoolsHandler(schoolName, amount);
    }

    @GetMapping(value = "cancer")
    public Map<String, Double> cancerHospitalDonHandler(@RequestParam("hospitalName") String hospitalName, @RequestParam("amount") Double amount) {
        return providersServices.cancerHospitalDonHandler(hospitalName, amount);
    }

    @GetMapping(value = "monthlyReceipt")
    public Map<String, Double> monthlyLandLineHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.monthlyLandLineHandler(mobileNumber, amount);
    }

    @GetMapping(value = "quarterReceipt")
    public Map<String, Double> quarterLandLineHandler(@RequestParam("mobileNumber") String mobileNumber, @RequestParam("amount") Double amount) {
        return providersServices.quarterLandLineHandler(mobileNumber, amount);
    }

}