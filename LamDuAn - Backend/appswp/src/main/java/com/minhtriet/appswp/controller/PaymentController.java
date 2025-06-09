package com.minhtriet.appswp.controller;

import com.minhtriet.appswp.entity.Payment;
import com.minhtriet.appswp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * API tạo payment khi user mua gói
     * (Nhận userId, packageId, paymentMethod, transactionId từ FE)
     */
    @PostMapping("/buy")
    public ResponseEntity<?> buyPackage(@RequestBody BuyPackageRequest request) {
        try {
            Payment payment = paymentService.processPackagePurchase(
                    request.getUserId(),
                    request.getPackageId(),
                    request.getPaymentMethod(),
                    request.getTransactionId()
            );
            Map<String, Object> resp = new HashMap<>();
            resp.put("success", true);
            resp.put("message", "Mua gói thành công!");
            resp.put("paymentId", payment.getPaymentId());
            resp.put("subscriptionEndDate", payment.getUser().getSubscriptionEndDate());
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Mua gói thất bại: " + e.getMessage()
            ));
        }
    }

    // DTO cho request mua gói
    public static class BuyPackageRequest {
        private Long userId;
        private Long packageId;
        private String paymentMethod;
        private String transactionId;

        // Getter/setter...
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getPackageId() { return packageId; }
        public void setPackageId(Long packageId) { this.packageId = packageId; }
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    }
}
