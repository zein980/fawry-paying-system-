package com.example.demo.transaction.service;


import com.example.demo.providers.repo.ProviderRepository;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.user.model.Account;
import com.example.demo.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class TransactionService {
    final TransactionRepository transactionRepository;
    final ProviderRepository providerRepository;
    final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, ProviderRepository providerRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public ResponseEntity<Void> addPaymentTransaction(Transaction transaction) {
        transaction.setType("payment");

        String paymentMethod = transaction.getPaymentMethod();
        if (!userRepository.existsById(transaction.getUserId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Account account = userRepository.findById(transaction.getUserId()).get();

        if (Objects.equals(paymentMethod, "cash")) {
            if (providerRepository.findById(transaction.getProviderId()).get().isCash()) {
                addTransaction(transaction);
            } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        } else if (Objects.equals(paymentMethod, "wallet")) {
            double wallet_balance = account.getWalletBalance();
            if (wallet_balance >= transaction.getAmount()) {
                account.setWalletBalance(wallet_balance - transaction.getAmount());
                addTransaction(transaction);
            } else return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        } else if (Objects.equals(paymentMethod, "visa")) {
            addTransaction(transaction);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}
