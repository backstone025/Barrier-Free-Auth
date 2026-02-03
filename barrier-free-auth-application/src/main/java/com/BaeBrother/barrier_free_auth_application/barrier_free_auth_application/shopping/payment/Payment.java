package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 결제 고유 식별자
    private Long orderId;   // 상품 정보 id
    private Long userId;    // 결제 사용자 id
    private String paymentMethod;   //결제 수단
    private Long amount; // 결제금액
    private String status; // 결제상태
    private LocalDateTime paidAt; //결제 완료 일시


    public Payment() {
    }

    public Payment(Long id, Long orderId, Long userId, String paymentMethod, Long amount, String status, LocalDateTime paidAt){
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }


}
