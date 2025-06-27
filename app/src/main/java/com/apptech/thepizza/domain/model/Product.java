package com.apptech.thepizza.domain.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    @SerializedName("category_id") private Integer categoryId;
    private String name;
    private BigDecimal price;
    @SerializedName("image_url") private String imageUrl;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
