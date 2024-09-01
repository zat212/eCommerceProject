package com.lastProject.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cus_id",referencedColumnName = "cus_id",nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    private float totalAmount;
    private Date orderDate;
    private String status;
    private String billingName;
    private String billingAddress;
    private String billingEmail;
    private String contactNumber1;
    private String contactNumber2;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;
    public Order() {
    }

    public Order(Customer customer, float totalAmount, Date orderDate, String status, String billingName, String billingAddress, String billingEmail, String contactNumber1, String contactNumber2, List<OrderItem> orderItem) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.billingName = billingName;
        this.billingAddress = billingAddress;
        this.billingEmail = billingEmail;
        this.contactNumber1 = contactNumber1;
        this.contactNumber2 = contactNumber2;
        this.orderItem = orderItem;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getContactNumber1() {
        return contactNumber1;
    }

    public void setContactNumber1(String contactNumber1) {
        this.contactNumber1 = contactNumber1;
    }

    public String getContactNumber2() {
        return contactNumber2;
    }

    public void setContactNumber2(String contactNumber2) {
        this.contactNumber2 = contactNumber2;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer=" + customer +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", billingName='" + billingName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", billingEmail='" + billingEmail + '\'' +
                ", contactNumber1='" + contactNumber1 + '\'' +
                ", contactNumber2='" + contactNumber2 + '\'' +
                ", orderItem=" + orderItem +
                '}';
    }
}
