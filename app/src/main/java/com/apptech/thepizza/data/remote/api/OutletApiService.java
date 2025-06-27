package com.apptech.thepizza.data.remote.api;

import com.apptech.thepizza.domain.model.Outlet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OutletApiService {

    @GET("outlet")
    Call<List<Outlet>> getAll();
}
