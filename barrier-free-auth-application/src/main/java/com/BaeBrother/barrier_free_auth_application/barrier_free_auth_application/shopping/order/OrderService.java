package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
반드시 명심할 것
- 어떠한 경우라도 Order 객체로 반환하지 않기
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AccountRepository accountRepository;

    /*
    createOrder -> 새로운 주문을 추가하는 method
    orderDate, isDone은 default값으로 채워넣을 것.
    id는 Spring에서 자동 설정하게 두었다.
    잘 모르겠으면 @GeneratedValue(strategy = GenerationType.IDENTITY)이거 공부해라
    calculatePrice를 통해 가격은 내부에서 처리하도록 한다.

    return : 해당 method의 성공여부
     */
    public boolean createOrder(long userId, long productId, long quantity) {
    }

    public Long calculatePrice(Long productId, Long quantity) {
    }

    /*
    updateOrder -> 기준 주문을 update하는 method
    주의할 점 : 기존 oder의 ID는 그대로 두게 만들어야 한다.

    return : 해당 method의 성공여부
     */
    public boolean updateOrder(long orderId, long userId, long productId, long quantity, boolean isDone) {
    }

    /*
    deleteOrder -> 기존 주문을 delete하는 method

    return : 해당 method의 성공여부
     */
    public boolean deleteOrder(Long orderId) {
    }

    /*
    getOrder -> order의 id로 DB의 정보를 끄집어와주는 method

    return : Order의 DTO로 반환
    - DTO(Data to object) : DB에 접근하지 않으며 단순히 보여주기 위해 담아놓은 객체로 이후에 JSON변환에 사용할 때 요구되는 형식이다.
     */
    public OrderDTO getOrder(Long orderId) {
    }

    /*
    getOrdersBuyUserId -> 사용자 id로 사용자가 주문한 목록을 반환하는 method

    return : Order들의 DTO를 List로 묶어서 반환할 것
     */
    public List<OrderDTO> getOrdersByUserId(Long userId) {
    }
}
