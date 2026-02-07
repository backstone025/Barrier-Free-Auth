package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment;

public class PaymentDTO {
    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.orderId = payment.getOrderId();
        this.userId = payment.getUserId();
        this.amount = payment.getAmount();
        this.status = payment.getStatus();
    }

    Long id;
    Long orderId;
    Long userId;
    Long amount;
    PaymentStatus status;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
