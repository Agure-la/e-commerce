package org.acme.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "session_id")
    private Double sessionId;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<OrderItem> orderItems;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    public Order() {
    }

    public Order(Integer id, Integer userId, Date createdDate, Double totalPrice, Double sessionId,
                 List<OrderItem> orderItems, User user) {
        this.id = id;
        this.userId = userId;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.sessionId = sessionId;
        this.orderItems = orderItems;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getSessionId() {
        return sessionId;
    }

    public void setSessionId(Double sessionId) {
        this.sessionId = sessionId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
