package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Có thể custom thêm nếu cần tra cứu giao dịch
}
