package com.apptech.thepizza.domain.model;

public class Cart {
    private Integer id;
    private Product product;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
