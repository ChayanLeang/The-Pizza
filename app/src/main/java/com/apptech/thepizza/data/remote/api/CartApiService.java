package com.apptech.thepizza.data.remote.api;

import com.apptech.thepizza.data.dto.CartDto;
import com.apptech.thepizza.data.remote.response.MessageResponse;
import com.apptech.thepizza.domain.model.Cart;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApiService {

    @GET("carts")
    Call<List<Cart>> getAll();

    @POST("carts/add")
    Call<MessageResponse> add(@Body CartDto cartDto);

    @GET("carts/get-total-price")
    Call<BigDecimal> getTotal();

    @DELETE("carts/delete/{id}")
    Call<MessageResponse> delete(@Path("id") Integer id);

    @DELETE("carts/delete-all")
    Call<MessageResponse> deleteAll();
}
