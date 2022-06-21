package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CartRequest {

    private List<CartItemRequest> cartItems;
    @JsonProperty("totalCost")
    private double totalCost;

    public CartRequest(List<CartItemRequest> cartItemsList, double totalCost) {
        this.cartItems = cartItemsList;
        this.totalCost = totalCost;
    }

    public List<CartItemRequest> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemRequest> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
