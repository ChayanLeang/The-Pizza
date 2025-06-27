package com.apptech.thepizza.data.remote.api;

import com.apptech.thepizza.domain.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductApiService {

    @GET("products/get-by-category-id/{categoryId}")
    Call<List<Product>> getByCategoryId(@Path("categoryId") Integer categoryId);
}
