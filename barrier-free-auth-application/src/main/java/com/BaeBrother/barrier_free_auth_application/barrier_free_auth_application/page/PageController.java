package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.page;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.ShoppingService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {
    @Autowired
    AccountService accountService;
    @Autowired
    ShoppingService shoppingService;

    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        Long userId = accountService.getId();
        String username = accountService.getUsername();
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);
        return "home";
    }

    @GetMapping(path = "/getUsers")
    public List<Account> users(ModelMap model) {
        return accountService.getAccounts();
    }

    @GetMapping(path = "/getOrders")
    public List<OrderDTO> orders(ModelMap model) {
        return shoppingService.getOrders();
    }

    @GetMapping(path = "/getOrder/{orderId}")
    public OrderDTO getOrder(@PathVariable("orderId") Long orderId) {
        return shoppingService.getOrder(orderId);
    }

    @GetMapping(path = "/getProducts")
    public List<ProductDTO> products(ModelMap model) {
        return shoppingService.getProducts();
    }
}