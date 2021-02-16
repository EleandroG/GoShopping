package com.goshopping.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price", length = 45, nullable = false)
    private double price;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "description", length = 45, nullable = false)
    private String description;

    @Column(name = "brand", length = 45, nullable = false)
    private double brand;

    public Product() {
    }

    public Product(Integer id, double price, String name, String description, double brand) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBrand() {
        return brand;
    }

    public void setBrand(double brand) {
        this.brand = brand;
    }
}
