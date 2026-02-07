package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    public Order() {
    }

    public Order(long id, long userId, long productId, long quantity, long price, LocalDate orderDate, boolean isDone) {
        this.id = id;
        // 주문한 계정 id
        this.userId = userId;
        this.productId = productId;
        // 상품 수량
        this.quantity = quantity;
        // 총 상품 가격
        this.price = price;
        this.orderDate = orderDate;
        // 주문 만료 여부
        this.isDone = isDone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private Long quantity;
    private Long price;
    private LocalDate orderDate;
    private Boolean isDone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
// 26/1/7 수정사항
// 1) long을 Wapper클래스인 Long으로 변환타입을 변경했습니다.(null사용을 하기 위해서)
// -> 다른 resource를 변경했습니다.