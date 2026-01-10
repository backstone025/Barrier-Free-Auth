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
        Long userId = accountService.getUserId();
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

    public Long paymentOrder(Long orderId, String token) {
        /*
        1. AuthenticationValidator의 hasAccess에서 권한 체크한다.
        2. order service의 complete order에서 주문이 완료됬다고 만든다.
        3. complete order메소드에서 payment에 기록을 남기도록 하고 반환을 해당 payment의 id로 반환하도록 한다.
        4. 해당 계정의 권한 사용 기록을 남기기 위해 AccessLogManager에서 사용한 권한을 남긴다.
        5. 반환으로 받은 payment의 id을 반환한다.
         */
        return 0l;
    }

    // payment

    public List<PaymentDTO> getPayments() {
        return paymentService.getPayments();
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
