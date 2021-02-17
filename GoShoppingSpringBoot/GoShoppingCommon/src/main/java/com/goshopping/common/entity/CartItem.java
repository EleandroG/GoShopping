package com.goshopping.common.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    //@JoinColumn(name = "product_id")
    @OneToMany
    private Set<Product> products = new HashSet<>();

    //TODO: Um mero atributo ou faço mesmo a ligação?
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int quantity;

    public CartItem() {
    }
    public CartItem(Customer customer, int quantity) {
        this.customer = customer;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /*public void addRole(Role role) {
        this.roles.add(role);
    }

     private Set<Role> roles = new HashSet<>();
    */

    public void addProduct(Product product) { this.products.add(product);}
}
