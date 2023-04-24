package com.example.demo.transaction.controller;

import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("transactions")
public class TransactionController {
    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(value = "search")
    public List<Transaction> getUserTransactions(@RequestParam("userId") Long userId) {
        return transactionService.getUserTransactions(userId);
    }

    @PostMapping(value = "pay")
    public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addPaymentTransaction(transaction);
    }
}
