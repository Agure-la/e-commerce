package org.acme.resource.request;

import org.acme.model.Order;

public class PlaceOrderRequest {

    private Integer id;
    private  Integer userId;
    private Double totalPrice;

    public PlaceOrderRequest() {
    }

    public PlaceOrderRequest(Order order) {
        this.setId(order.getId());
        this.setUserId(order.getUserId());
        this.setTotalPrice(order.getTotalPrice());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
