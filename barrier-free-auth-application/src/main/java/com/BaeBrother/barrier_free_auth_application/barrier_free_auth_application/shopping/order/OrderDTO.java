package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.order;

import java.time.LocalDate;

public class OrderDTO {
    private long id;
    private String productName;
    private long quantity;
    private long totalPrice;
    private LocalDate orderDate;
    private boolean isDone;

    public OrderDTO() {
    }

    public OrderDTO(Order order, String productName) {
        this.id = order.getId();
        this.productName = productName;
        this.quantity = order.getQuantity();
        this.totalPrice = order.getPrice();
        this.orderDate = order.getOrderDate();
        this.isDone = order.isDone();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
