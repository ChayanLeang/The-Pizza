package com.apptech.thepizza.data.repository;

import androidx.annotation.NonNull;

import com.apptech.thepizza.data.remote.RetrofitClient;
import com.apptech.thepizza.data.remote.api.ProductApiService;
import com.apptech.thepizza.domain.model.Product;
import com.apptech.thepizza.domain.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepositoryImpl implements ProductRepository {
    private final ProductApiService productApiService;

    public ProductRepositoryImpl(){
        productApiService = RetrofitClient.getInstance().create(ProductApiService.class);
    }

    @Override
    public void getByCategoryId(Integer categoryId, Consumer<List<Product>> onSuccess,Runnable onFailure){
        productApiService.getByCategoryId(categoryId).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call,
                                   @NonNull Response<List<Product>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        onSuccess.accept(response.body());
                    }
                    else{
                        onSuccess.accept(Collections.emptyList());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                onFailure.run();
            }
        });
    }
}
