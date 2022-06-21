package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AddToCartRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("productId")
    private Integer productId;
    @JsonProperty("quantity")
    private Integer quantity;

    public AddToCartRequest() {
    }

    @Override
    public String toString() {
        return "AddToCartRequest{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
