package com.lastProject.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order_reports")
public class OrderReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int report_id;
    private int orderId;
    private int customerId;
    private String customerName;
    private double totalPrice;
    private Date orderDate;
    private String billingName;
    private String billingAddress;
    private String billingEmail;
    private String contactNumber1;
    private String contactNumber2;
    private String status;

    public OrderReport() {
    }

    public OrderReport(int orderId, int customerId, String customerName, double totalPrice, Date orderDate, String billingName, String billingAddress, String billingEmail, String contactNumber1, String contactNumber2, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.billingName = billingName;
        this.billingAddress = billingAddress;
        this.billingEmail = billingEmail;
        this.contactNumber1 = contactNumber1;
        this.contactNumber2 = contactNumber2;
        this.status = status;
    }


    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
