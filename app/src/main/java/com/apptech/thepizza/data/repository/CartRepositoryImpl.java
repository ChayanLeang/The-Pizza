package com.apptech.thepizza.data.repository;

import androidx.annotation.NonNull;

import com.apptech.thepizza.data.dto.CartDto;
import com.apptech.thepizza.data.remote.RetrofitClient;
import com.apptech.thepizza.data.remote.api.CartApiService;
import com.apptech.thepizza.data.remote.response.MessageResponse;
import com.apptech.thepizza.domain.model.Cart;
import com.apptech.thepizza.domain.repository.CartRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepositoryImpl implements CartRepository {
    private final CartApiService cartApiService;

    public CartRepositoryImpl(){
        cartApiService = RetrofitClient.getInstance().create(CartApiService.class);
    }

    @Override
    public void getAll(Consumer<List<Cart>> onSuccess,Runnable onFailure) {
        cartApiService.getAll().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Cart>> call, @NonNull Response<List<Cart>> response) {
                if(response.body() != null){
                    onSuccess.accept(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Cart>> call, @NonNull Throwable t) {
                onFailure.run();
            }
        });
    }

    @Override
    public void add(CartDto cartDto) {
        cartApiService.add(cartDto).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MessageResponse> call,
                                   @NonNull Response<MessageResponse> response) {}

            @Override
            public void onFailure(@NonNull Call<MessageResponse> call, @NonNull Throwable t) {}
        });
    }

    @Override
    public void getTotal(Consumer<BigDecimal> onSuccess) {
        cartApiService.getTotal().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<BigDecimal> call, @NonNull Response<BigDecimal> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        onSuccess.accept(response.body());
                    }
                    else{
                        onSuccess.accept(BigDecimal.ZERO);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BigDecimal> call, @NonNull Throwable t) {}
        });
    }

    @Override
    public void delete(Integer id,Runnable onSuccess) {
        cartApiService.delete(id).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(@NonNull Call<MessageResponse> call,
                                   @NonNull Response<MessageResponse> response) {
                if(response.isSuccessful()){
                    onSuccess.run();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageResponse> call, @NonNull Throwable t) {}
        });
    }

    @Override
    public void deleteAll() {
        cartApiService.deleteAll().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MessageResponse> call,
                                   @NonNull Response<MessageResponse> response) {}

            @Override
            public void onFailure(@NonNull Call<MessageResponse> call, @NonNull Throwable t) {}
        });
    }
}
