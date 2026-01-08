package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order;

import java.time.LocalDate;

public class OrderDTO {
    private Long id;
    private String productName;
    private Long quantity;
    private Long totalPrice;
    private LocalDate orderDate;
    private Boolean isDone;

    public OrderDTO() {
    }

    public OrderDTO(Order order, String productName) {
        this.id = order.getId();
        this.productName = productName;
        this.quantity = order.getQuantity();
        this.totalPrice = order.getPrice();
        this.orderDate = order.getOrderDate();
        this.isDone = order.getDone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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
