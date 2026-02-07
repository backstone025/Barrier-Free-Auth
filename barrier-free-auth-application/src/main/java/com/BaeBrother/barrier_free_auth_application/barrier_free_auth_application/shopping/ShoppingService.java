package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.PaymentDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.PaymentService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.ProductDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProductService productService;

    // Order

    public Long createOrder(OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    public Long updateOrder(Long orderId, OrderDTO orderDTO) {

        if (orderService.getOrder(orderId) != null) {
            // 이미 완료된 order는 수정할 수 없다.
            if (!orderService.getOrder(orderId).getDone()) {
                return orderService.updateOrder(orderDTO, orderId);
            }
        }
        return null;
    }

    // 추후, account 권한 구현 완료 후, 확인하는 절차를 추가할 계획이다.
    public boolean completeOrder(Long orderId) {
        boolean state = orderService.completeOrder(orderId);
        return state;
    }

    public boolean deleteOrder(Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    public OrderDTO getOrder(Long orderId) {
        Long userId = accountService.getUserId();
        return orderService.getOrder(orderId);
    }

    public List<OrderDTO> getOrders() {
        Long userId = accountService.getUserId();
        return orderService.getOrdersByUserId(userId);
    }

    // payment

    public List<PaymentDTO> getPaymentsByUserId() {
        Long userId = accountService.getUserId();
        return paymentService.getPaymentsByUserId(userId);
    }

    public PaymentDTO getPaymentById(Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    // Product

    public Long createProduct(ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    public Long updateProduct(ProductDTO productDTO, Long productId) {
        if (productService.getProduct(productId) != null) {
            return productService.updateProduct(productDTO, productId);
        }

        return null;
    }

    public boolean deleteProduct(Long productId) {
        boolean state = productService.deleteProduct(productId);

        return state;
    }

    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    public ProductDTO getProduct(Long productId) {
        return productService.getProduct(productId);
    }
}
