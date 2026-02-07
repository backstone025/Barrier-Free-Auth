package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.mokingPaymentServer;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.PaymentDTO;

public interface PaymentClient {
    public PaymentServerStatus getPaymentServerStatus(PaymentDTO paymentDTO);
}