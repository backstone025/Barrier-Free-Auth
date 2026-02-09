package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.mokingPaymentServer.LocalFilePaymentClient;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.mokingPaymentServer.PaymentServerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentRepository paymentRepository;

    // for test
    private LocalFilePaymentClient localFilePaymentClient = new LocalFilePaymentClient();

    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository
                .getPaymentByOrderIdAndStatus(orderId, PaymentStatus.REJECTED);
        if (payment == null) {
            return null;
        } else return new PaymentDTO(payment);
    }

    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        return paymentRepository.getPaymentsByUserId(userId)
                .stream().map(p -> new PaymentDTO(p)).toList();
    }

    public PaymentDTO getPaymentById(Long paymentId) {
        return new PaymentDTO(paymentRepository.getById(paymentId));
    }

    public PaymentDTO createPayment(OrderDTO orderDTO) {
        Payment payment = new Payment();
        if (orderDTO.getId() != null) {
            payment.setOrderId(orderDTO.getId());
        }
        if (orderDTO.getTotalPrice() >= 0) {
            payment.setAmount(orderDTO.getTotalPrice());
        }
        payment.setUserId(accountService.getAccountId());
        payment.setPaidAt(LocalDateTime.now());
        payment.setStatus(PaymentStatus.REJECTED);

        paymentRepository.save(payment);

        return new PaymentDTO(payment);
    }

    public PaymentStatus completePayment(PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.getById(paymentDTO.getId());
        PaymentServerStatus serverStatus = localFilePaymentClient.getPaymentServerStatus(paymentDTO);
        if(serverStatus.equals(PaymentServerStatus.ALLOWED))
            payment.setStatus(PaymentStatus.COMPLETED);
        else if(serverStatus.equals(PaymentServerStatus.REJECTED))
            payment.setStatus(PaymentStatus.REJECTED);

        paymentRepository.save(payment);

        return payment.getStatus();
    }
}
