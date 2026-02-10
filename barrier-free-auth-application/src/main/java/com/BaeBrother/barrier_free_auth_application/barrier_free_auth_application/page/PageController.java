package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.page;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.MainAccount;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity.TokenService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.ShoppingService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.Order;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.PaymentDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.Product;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PageController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        Long userId = accountService.getAccountId();
        String username = accountService.getLoginId();
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);
        return "home";
    }

    // users

    @GetMapping(path = "/users")
    public List<Account> getUsers(ModelMap model) {
        return accountService.getAccounts();
    }

    @GetMapping(path = "/users/{userId}")
    public Account getUser(@PathVariable Long userId) {
        return accountService.getAccount(userId);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<MainAccount> createMainUser(@RequestBody MainAccount account) {
        // id 비워놓기
        account.setId(null);

        URI location;
        Long accountId = accountService.saveMainAccount(account);
        if (accountId != null) {
            location = URI.create("/users/" + accountId);
        } else {
            // error 발생시 이둉하는 주도를 임시로 구현했다.
            location = URI.create("/users");
        }

        return ResponseEntity.created(location).build();
    }

    // orders

    @GetMapping(path = "/orders")
    public List<OrderDTO> orders(ModelMap model) {
        return shoppingService.getOrders();
    }

    @GetMapping(path = "/orders/{orderId}")
    public OrderDTO getOrder(@PathVariable("orderId") Long orderId) {
        return shoppingService.getOrder(orderId);
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Long orderId = shoppingService.createOrder(orderDTO);
        URI location;

        if (orderId != null) {
            location = URI.create("/orders/" + orderId);
        } else location = URI.create("/orders");

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(path = "/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDTO orderDTO) {
        Long updatedOrderId = shoppingService.updateOrder(orderId, orderDTO);
        URI location;

        if (updatedOrderId != null) {
            location = URI.create("/orders/" + updatedOrderId);
        } else location = URI.create("/orders");

        return ResponseEntity.created(location).build();

    }


    @PatchMapping(path = "/orders/{orderId}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable("orderId") Long orderId) {
        boolean state = shoppingService.completeOrder(orderId);
        if (state) {
            // 상태 양호
            return ResponseEntity.created(URI.create("/orders/" + orderId)).build();
        }else {
            // 문제 발생
            return ResponseEntity.badRequest().body("결제 승인 거절");
        }
    }

    @DeleteMapping(path = "/orders/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        if (shoppingService.deleteOrder(orderId)) {
            // 삭제 성공 메세지
        } else {
            // 삭제 실패 메세지
        }
    }

    // payments
    @GetMapping(path = "/payments")
    public List<PaymentDTO> getPayments() {
        return shoppingService.getPaymentsByUserId();
    }

    @GetMapping(path = "/payments/{paymentId}")
    public PaymentDTO getPayment(@PathVariable("paymentId") Long paymentId) {
        return shoppingService.getPaymentById(paymentId);
    }

    // products

    @GetMapping(path = "/products")
    public List<ProductDTO> products(ModelMap model) {
        return shoppingService.getProducts();
    }

    @GetMapping(path = "/products/{productId}")
    public ProductDTO getProduct(@PathVariable("productId") Long productId) {
        return shoppingService.getProduct(productId);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Long productId = shoppingService.createProduct(productDTO);
        URI location;

        if (productId != null) {
            location = URI.create("/products/" + productId);
        } else location = URI.create("/products");

        return ResponseEntity.created(location).build();
    }

    @PatchMapping(path = "/products/{products}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("products") Long productId, @RequestBody ProductDTO productDTO) {
        Long updatedProductId = shoppingService.updateProduct(productDTO, productId);
        URI location;
        if (updatedProductId != null) {
            location = URI.create("/products/" + updatedProductId);
        } else location = URI.create("/products");

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/products/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        if (shoppingService.deleteProduct(productId)) {
            // 삭제 성공 메세지
        } else {
            // 삭제 실패 메세지
        }

    }
}