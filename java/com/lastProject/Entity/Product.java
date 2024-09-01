package com.lastProject.Entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productAvailable;
    private String productCategory;
    private Date addedDate;


    @Column(name = "productImage")
    private String productImage;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Product() {
    }

    public Product(String productName, String productDescription, float productPrice, int productAvailable, String productCategory, Date addedDate, String productImage, List<OrderItem> orderItems) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productAvailable = productAvailable;
        this.productCategory = productCategory;
        this.addedDate = addedDate;
        this.productImage = productImage;
        this.orderItems = orderItems;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(int productAvailable) {
        this.productAvailable = productAvailable;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productAvailable=" + productAvailable +
                ", productCategory='" + productCategory + '\'' +
                ", addedDate=" + addedDate +
                ", productImage=" + productImage +
                ", orderItems=" + orderItems +
                '}';
    }
}
