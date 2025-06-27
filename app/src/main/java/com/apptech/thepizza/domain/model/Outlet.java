package com.apptech.thepizza.domain.model;

import com.google.gson.annotations.SerializedName;

public class Outlet {
    private Integer id;
    private String name;
    private String address;
    @SerializedName("image_url") private String imageUrl;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
