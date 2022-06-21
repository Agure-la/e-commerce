package org.acme.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.acme.model.Order;

public class OrderRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("userId")
    private Integer userId;

    public OrderRequest(Order order) {
        this.setId(order.getId());
        this.setUserId(order.getUserId());
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
}
