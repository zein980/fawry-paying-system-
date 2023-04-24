package com.example.demo;

import com.example.demo.discount.model.Discount;
import com.example.demo.discount.repository.DiscountRepository;
import com.example.demo.providers.models.Provider;
import com.example.demo.providers.repo.ProviderRepository;
import com.example.demo.refund.model.Refund;
import com.example.demo.refund.repository.RefundRepository;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.user.model.Account;
import com.example.demo.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(RefundRepository refundRepository, UserRepository userRepository, ProviderRepository providerRepository, DiscountRepository discountRepository, TransactionRepository transactionRepository) {
        return args -> {
            // Admin
            userRepository.save(new Account(true, "admin", "123", "admin1", 1L, 100));
            userRepository.save(new Account(true, "omar", "123", "omar", 2L, 100));
            userRepository.save(new Account(true, "zeinb", "123", "zeinb", 3L, 100));
            userRepository.save(new Account(true, "ahmed", "123", "ahmed", 4L, 100));
            userRepository.save(new Account(true, "amr", "123", "amr", 5L, 100));


            // Providers
            providerRepository.save(new Provider(1L, 2, "weInternet", "Internet", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(2L, 2, "orangeInternet", "Internet", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(3L, 1, "etisalatInternet", "Internet", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(4L, 2, "vodafoneInternet", "Internet", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(5L, 1, "weMob", "Recharge", "MobileNumber-Amount", false));
            providerRepository.save(new Provider(6L, 3, "etisalatMob", "Recharge", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(7L, 3, "orangeMob", "Recharge", "MobileNumber-Amount", false));
            providerRepository.save(new Provider(8L, 2, "vodafoneMob", "Recharge", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(9L, 1, "monthlyReceipt", "Landline", "MobileNumber-Amount", false));
            providerRepository.save(new Provider(10L, 2, "quarterReceipt", "Landline", "MobileNumber-Amount", true));
            providerRepository.save(new Provider(11L, 3, "school", "Donation", "schoolName-Amount", true));
            providerRepository.save(new Provider(12L, 2, "NGOs", "Donation", "charity Name-Amount", false));
            providerRepository.save(new Provider(13L, 4, "cancer", "Donation", "hospitalName-Amount", true));

            // Discounts
            discountRepository.save(new Discount(1L, "Recharge", 3.2));
            discountRepository.save(new Discount(2L, "Donation", 12.5));
            discountRepository.save(new Discount(3L, "ALL", 2.5));

            // Transactions
            transactionRepository.save(new Transaction(1L, 3L, 3L, 10.0, "payment", "cash"));
            transactionRepository.save(new Transaction(2L, 3L, 2L, 11.0, "rechargeWallet", "visa"));
            transactionRepository.save(new Transaction(3L, 1L, 3L, 54.0, "refund", "wallet"));

            // Refunds
            refundRepository.save(new Refund(1L, 1L));
            refundRepository.save(new Refund(2L, 3L));
        };
    }
}