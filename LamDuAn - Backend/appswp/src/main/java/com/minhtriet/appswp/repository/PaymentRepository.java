package com.minhtriet.appswp.repository;

import com.minhtriet.appswp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Bạn có thể bổ sung thêm các phương thức nếu muốn
}
