package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderItemsRequest {

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("productId")
    private int productId;

    public OrderItemsRequest () {}

    public OrderItemsRequest(double price, int quantity, int orderId, int productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
