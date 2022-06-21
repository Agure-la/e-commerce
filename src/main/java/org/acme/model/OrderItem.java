package org.acme.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @Column(name = "productId")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "productId",referencedColumnName = "id",insertable = false,updatable = false)
    private Product product;

    public OrderItem() {
    }

    public OrderItem(Integer orderItemId, int productId,
                     int quantity, Long price, Date createdDate, Order order, Product product) {
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.createdDate = createdDate;
        this.order = order;
        this.product = product;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrderId(int orderId) {
    }
}
