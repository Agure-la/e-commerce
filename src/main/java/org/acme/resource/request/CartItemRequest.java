package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.acme.model.Cart;
import org.acme.model.Product;

public class CartItemRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("product")
    private Product product;

    public CartItemRequest(Cart cart){
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
