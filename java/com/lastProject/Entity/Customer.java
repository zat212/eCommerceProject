package com.lastProject.Entity;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cus_id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private String cusName;
    @Column(nullable = false)
    private String cusEmail;
    @Column(nullable = false)
    private String cusPassword;
    @Column(nullable = false)
    private String deliveryAddress;
    @Column(nullable = false)
    private String phoneNumber;
    private Date registeredDate;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Order> orders;

    @PrePersist
    @PreUpdate
    private void generateFullName() {
        this.cusName = firstName + " " + lastName;
    }

    public Customer() {
    }

    public Customer(String firstName, String lastName, String cusEmail, String cusPassword, String deliveryAddress, String phoneNumber, Date registeredDate, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cusEmail = cusEmail;
        this.cusPassword = cusPassword;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        this.registeredDate = registeredDate;
        this.orders = orders;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public void setCusPassword(String cusPassword) {
        this.cusPassword = cusPassword;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_id=" + cus_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusEmail='" + cusEmail + '\'' +
                ", cusPassword='" + cusPassword + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registeredDate=" + registeredDate +
                ", orders=" + orders +
                '}';
    }
}
