package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment getPaymentByOrderIdAndStatus(Long orderId,  PaymentStatus status);
    List<Payment> getPaymentsByUserId(Long userId);
}
