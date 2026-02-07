package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account.AccountService;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.payment.*;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.Product;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/*
반드시 명심할 것
- 어떠한 경우라도 Order 객체로 반환하지 않기
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PaymentService paymentService;

    /*
    createOrder -> 새로운 주문을 추가하는 method
    orderDate, isDone은 default값으로 채워넣을 것.
    id는 Spring에서 자동 설정하게 두었다.
    잘 모르겠으면 @GeneratedValue(strategy = GenerationType.IDENTITY)이거 공부해라
    calculatePrice를 통해 가격은 내부에서 처리하도록 한다.

    return : 해당 method의 성공여부
     */
    public Long createOrder(OrderDTO orderDTO) {
        Long userId = accountService.getUserId();
        Long productId = productRepository.findByName(orderDTO.getProductName()).getId();
        Long quantity = orderDTO.getQuantity();
        if (userId != null && productId != null && quantity != null) {
            LocalDate date = LocalDate.now();
            Order order = new Order();
            order.setUserId(userId);
            order.setProductId(productId);
            order.setQuantity(quantity);
            order.setPrice(calculatePrice(productId, quantity));
            order.setOrderDate(date);
            order.setDone(false);    // 주문을 준비 혹은 미완료상태를 위해 초기 false사용
            Long orderId = orderRepository.save(order).getId();
            return orderId;
        } else return null;
    }

    public Long calculatePrice(Long productId, Long quantity) {
        if (productId != null || quantity != null) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                return product.getPrice() * quantity;
            }
        }
        return null;
    }

    /*
    updateOrder -> 기준 주문을 update하는 method
    주의할 점 : 기존 oder의 ID는 그대로 두게 만들어야 한다.

    return : 해당 method의 성공여부
     */

    public Long updateOrder(OrderDTO orderDTO, Long orderId) {
        Long userId = accountService.getUserId();
        Long productId = productRepository.findByName(orderDTO.getProductName()).getId();
        Long quantity = orderDTO.getQuantity();
        boolean isDone = false;

        Optional<Order> orderBoolean = orderRepository.findById(orderId);
        if (orderBoolean.isPresent()) {
            LocalDate date = LocalDate.now();
            Order order = orderBoolean.get();
            order.setUserId(userId);
            if (productId != null) {
                order.setProductId(productId);
            }
            if (quantity != null) {
                order.setQuantity(quantity); // 가격도 같이 변경시켜야 할듯하다.
            }
            order.setPrice(calculatePrice(productId, quantity));
            order.setDone(isDone);
            order.setOrderDate(date); // !!업데이트 정보도 필요할 거 같아서 추가했다.
            return orderRepository.save(order).getId();
        } else return null;
    } // !!업데이트를 하기 이전의 로그 데이터를 저장할 필요가 있지 않을까? @Audited가 자동으로 변경이력을 저장해주는 어노테이션이 있는데 설정과 DB 용량이 많이 필요할 수 있다는 단점이 있다.

    /*
    completeOrder -> 주문이 완료됨을 update하는 method

    return 성사 여부
     */
    public boolean completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            // payment 처리
            PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
            // completeOrder 이력이 없을 경우
            if (payment == null) {
                payment = paymentService.createPayment(orderToDTO(order));
            }

            // payment 완료 처리
            PaymentStatus paymentStatus = paymentService.completePayment(payment);

            // 주문 완료 처리
            if (paymentStatus == PaymentStatus.COMPLETED) {
                order.setDone(true);
                orderRepository.save(order);
            }else if (paymentStatus == PaymentStatus.REJECTED) {
                order.setDone(false);
                orderRepository.save(order);
            }
            return true;
        }
        return false;
    }

    /*
    deleteOrder -> 기존 주문을 delete하는 method

    return : 해당 method의 성공여부
     */
    public boolean deleteOrder(Long orderId) {
        Optional<Order> orderBoolean = orderRepository.findById(orderId);
        if (orderBoolean.isPresent()) {
            orderRepository.delete(orderBoolean.get());
            return true;
        } else return false;
    }

    /*
    getOrder -> order의 id로 DB의 정보를 끄집어와주는 method

    return : Order의 DTO로 반환
    - DTO(Data to object) : DB에 접근하지 않으며 단순히 보여주기 위해 담아놓은 객체로 이후에 JSON변환에 사용할 때 요구되는 형식이다.
     */
    public OrderDTO getOrder(Long orderId) {
        Optional<Order> orderBoolean = orderRepository.findById(orderId);
        if (orderBoolean.isPresent()) {
            Order order = orderBoolean.get();
            // getOrderByUserId의 map에서 쓸데 없는 중복을 피하기 위해 기존 id를 매개변수로 받는 형태에서 객체로 받는걸로 바꿨다.
            return orderToDTO(order);
        }
        return null;
    }

    public OrderDTO orderToDTO(Order order) {
        Product product = productRepository.findById(order.getProductId()).orElse(null);
        if (product != null) {
            return new OrderDTO(order, product.getName());
        }
        return null;
    }

    //함수로 만들고 ortoDTO이름으로 만들고 map에서 만들어라 마지막에 toArray하면된다.
    /*
    getOrdersBuyUserId -> 사용자 id로 사용자가 주문한 목록을 반환하는 method

    return : Order들의 DTO를 List로 묶어서 반환할 것
     */
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderToDTO(order)).toList();
        return orderDTOList;
    }
}
