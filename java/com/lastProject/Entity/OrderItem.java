package com.lastProject.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderItem_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    private Date orderDate;

    private int quantity;
    private float price;

    public OrderItem() {
    }

//    public OrderItem(int orderItem_id, Order order, Product product, Date orderDate, int quantity, float price) {
//        this.orderItem_id = orderItem_id;
//        this.order = order;
//        this.product = product;
//        this.orderDate = orderDate;
//        this.quantity = quantity;
//        this.price = price;
//    }

    public OrderItem(Order order, Product product, Date orderDate, int quantity, float price) {
        this.order = order;
        this.product = product;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.price = price;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderItem_id() {
        return orderItem_id;
    }

    public void setOrderItem_id(int orderItem_id) {
        this.orderItem_id = orderItem_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItem_id=" + orderItem_id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
