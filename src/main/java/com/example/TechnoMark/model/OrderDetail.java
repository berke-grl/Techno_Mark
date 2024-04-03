package com.example.TechnoMark.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerId;
    @OneToMany(mappedBy = "orderDetail")
    private List<Product> products;
    private Date orderDate;
    private int deliveryHours;

    public OrderDetail() {
    }

    public OrderDetail(int customerId, List<Product> product, Date orderDate, int deliveryHours) {
        this.customerId = customerId;
        this.products = product;
        this.orderDate = orderDate;
        this.deliveryHours = deliveryHours;
    }

    public OrderDetail(int id, int customerId, List<Product> product, Date orderDate, int deliveryHours) {
        this.id = id;
        this.customerId = customerId;
        this.products = product;
        this.orderDate = orderDate;
        this.deliveryHours = deliveryHours;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        if (products.isEmpty()) {
            return new ArrayList<Product>();
        }
        return products;
    }

    public void setProducts(List<Product> product) {
        this.products = product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(int deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryHours=" + deliveryHours +
                '}';
    }

}
