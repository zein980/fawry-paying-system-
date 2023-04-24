package com.example.demo.user.service;

import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.user.model.Account;
import com.example.demo.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<Void> addUser(Account account) {
        if (userRepository.findByEmail(account.getEmail()).isEmpty()) {
            userRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public Optional<Account> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Account> findByEmailPass(String email, String password) {
        return userRepository.findByEmailPass(email, password);
    }

    public ResponseEntity<Void> chargeWallet(Long userId, double amount) {
        if (!userRepository.existsById(userId)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Account account = userRepository.findById(userId).get();
        account.setWalletBalance(account.getWalletBalance() + amount);
        userRepository.save(account);
        transactionRepository.save(new Transaction(null, userId, null, amount, "walletRecharge", "visa"));

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}