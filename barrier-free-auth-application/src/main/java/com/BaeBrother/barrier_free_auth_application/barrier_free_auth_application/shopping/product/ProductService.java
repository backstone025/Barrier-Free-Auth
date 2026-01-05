package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.Account;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountRepository;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderDTO;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
반드시 명심할 것
- 어떠한 경우라도 Service 객체로 반환하지 않기
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrderRepository orderRepository;

    //createProduct -> 새로운 주문을 추가하는 method
    public boolean createProduct(String name, String description, long price) {
        if (name != null && description != null && price >= 0) {
            // 객체 생성하고, setter method를 통해 매개변수 값대로 값을 넣는다.
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);

            // 완성된 새 Product가 DB에 저장될 수 있도록 설정한다.
            productRepository.save(product);

            // DB에 문제없이 추가했으므로 true를 반환한다.
            return true;
        } else {
            // 만일 이름, 설명, 가격이 하나라도 제대로 입력되지 않으면 false를 반환한다.
            return false;
        }
    }


   // updateProduct -> 기준 주문을 update하는 method
    public boolean updateProduct(long productId, String name, String description, long price) {
        if (name != null && description != null && price >= 0) {
            // DB에서 주어진 Id를 가진 Product 객체를 가져온다.
            Product product = productRepository.findById(productId).get();

            if (product != null) {
                // 그 객체의 정보를 수정한다.
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);

                // 수정이 완료된 Product를 등록한다.
                productRepository.save(product);
                // 설공했음을 알리기 위해 true로 반환한다.
                return true;
            }
        }
        // 잘못된 정보 입력임을 알리기 위해 false를 반환한다.
        return false;
    }


    // deleteProduct -> 기존 주문을 delete하는 method
    public boolean deleteProduct(Long productId) {
        if(productId != null) {
            productRepository.deleteById(productId);
            return true;
        }
        else
            return false;
    }


    //getProduct -> order의 id로 DB의 정보를 끄집어와주는 method
    public ProductDTO getProduct(Long productId) {
        try{
            Product product = productRepository.findById(productId).get();
            ProductDTO productDTO = new ProductDTO(product);
            return productDTO;
        } catch (NoSuchElementException e) {return null;}
    }

    /*
    getProductssBuyUserId -> 사용자 id로 사용자가 주문한 목록을 반환하는 method
    return : Product들의 DTO를 List로 묶어서 반환할 것
     */
    public List<OrderDTO> getProductsByUserId(Long userId) {
        boolean userExists = accountRepository.findById(userId).isPresent();
        if (userExists) {
            List<OrderDTO> orderDTOS = orderRepository.findByUserId(userId);
            return orderDTOS;
        }
        else
            return null;
        //종료
    }
}
