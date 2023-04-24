package com.example.demo.refund.service;


import com.example.demo.refund.model.Refund;
import com.example.demo.refund.repository.RefundRepository;
import com.example.demo.transaction.model.Transaction;
import com.example.demo.transaction.repository.TransactionRepository;
import com.example.demo.transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RefundService {
    final RefundRepository refundRepository;
    final TransactionService transactionService;
    final TransactionRepository transactionRepository;

    public RefundService(RefundRepository refundRepository, TransactionRepository transactionRepository, TransactionService transactionService, TransactionRepository transactionRepository1) {
        this.refundRepository = refundRepository;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository1;
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    public ResponseEntity<Void> createRefund(Long transactionID) {
        if (transactionRepository.existsById(transactionID)) {
            refundRepository.save(new Refund(null, transactionID));
        } else return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> acceptRefund(Long refundId) {
        if (!refundRepository.existsById(refundId)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        Refund refund = refundRepository.findById(refundId).get();
        Transaction t = transactionRepository.findById(refund.getTransactionId()).get();
        transactionService.addTransaction(new Transaction(null, t.getUserId(), t.getProviderId(), t.getAmount(), "refund", t.getPaymentMethod()));
        refundRepository.deleteById(refundId);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> rejectRefund(Long refundId) {
        if (!refundRepository.existsById(refundId)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        refundRepository.deleteById(refundId);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
