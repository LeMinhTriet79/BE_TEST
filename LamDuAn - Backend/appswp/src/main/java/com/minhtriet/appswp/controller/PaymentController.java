package com.minhtriet.appswp.controller;

import com.minhtriet.appswp.dto.PaymentRequestDTO;
import com.minhtriet.appswp.entity.Payment;
import com.minhtriet.appswp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin // Cho phép FE truy cập API
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * API thanh toán gói thành viên.
     * FE truyền vào userId, packageId, amount, paymentMethod, transactionId (nếu có).
     */
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        Payment payment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.ok(payment);
    }
}
