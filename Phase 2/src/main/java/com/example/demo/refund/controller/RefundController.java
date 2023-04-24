package com.example.demo.refund.controller;

import com.example.demo.refund.model.Refund;
import com.example.demo.refund.service.RefundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("refunds")
public class RefundController {
    final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @GetMapping(value = "/all")
    public List<Refund> getAllRefund() {
        return refundService.getAllRefunds();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createRefund(@RequestParam("transactionID") Long transactionID) {
        return refundService.createRefund(transactionID);
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptRefund(@RequestParam("refundId") Long refundId) {
        return refundService.acceptRefund(refundId);
    }

    @PostMapping("/reject")
    public ResponseEntity<Void> rejectRefund(@RequestParam("refundId") Long refundId) {
        return refundService.rejectRefund(refundId);
    }
}