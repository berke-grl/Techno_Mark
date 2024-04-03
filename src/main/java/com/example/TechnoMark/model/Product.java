package com.example.TechnoMark.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private int stock;
    @ManyToOne
    private OrderDetail orderDetail;

    public Product() {
    }

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price, int stock, OrderDetail orderDetail) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.orderDetail = orderDetail;
    }

    public Product(Integer id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public OrderDetail getOrder() {
        return orderDetail;
    }

    public void setOrder(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
