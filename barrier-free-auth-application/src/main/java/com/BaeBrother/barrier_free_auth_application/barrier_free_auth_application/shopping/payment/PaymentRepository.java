package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    //속성추가하기
}
