package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderService;
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
    private ProductService productService;

    // Order

    public boolean createOrder(Long productId, Long quantity) {
        Long userId = accountService.getId();
        boolean state = orderService.createOrder(userId, productId, quantity);

        return state;
    }

    public boolean updateOrder(Long orderId, Long productId, Long quantity) {
        Long userId = accountService.getId();
        boolean isDone = orderService.getOrder(orderId).getDone();
        boolean state = orderService.updateOrder(orderId, userId, productId, quantity, isDone);

        return state;
    }

    // 추후, account 권한 구현 완료 후, 확인하는 절차를 추가할 계획이다.
    public boolean completeOrder(Long orderId) {
        Long userId = accountService.getId();
        boolean state = orderService.completeOrder(orderId);

        return state;
    }

    public boolean deleteOrder(Long orderId) {
        Long userId = accountService.getId();
        boolean state = orderService.deleteOrder(orderId);

        return state;
    }

    public OrderDTO getOrder(Long orderId) {
        Long userId = accountService.getId();
        return orderService.getOrder(orderId);
    }

    public List<OrderDTO> getOrders() {
        Long userId = accountService.getId();
        return orderService.getOrdersByUserId(userId);
    }

    // Product

    public boolean createProduct(String productname, String Description, Long price) {
        boolean state = productService.createProduct(productname, Description, price);

        return state;
    }

    public boolean updateProduct(Long productId, String productname, String Description, Long price) {
        boolean state = productService.updateProduct(productId, productname, Description, price);

        return state;
    }

    public boolean deleteProduct(Long productId) {
        boolean state = productService.deleteProduct(productId);

        return state;
    }

    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }
}
