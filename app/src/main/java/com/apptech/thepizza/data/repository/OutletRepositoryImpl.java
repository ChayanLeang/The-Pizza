package com.apptech.thepizza.data.repository;

import androidx.annotation.NonNull;

import com.apptech.thepizza.data.remote.RetrofitClient;
import com.apptech.thepizza.data.remote.api.OutletApiService;
import com.apptech.thepizza.domain.model.Outlet;
import com.apptech.thepizza.domain.repository.OutletRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutletRepositoryImpl implements OutletRepository {
    private final OutletApiService outletApiService;

    public OutletRepositoryImpl(){
        outletApiService = RetrofitClient.getInstance().create(OutletApiService.class);
    }

    @Override
    public void getAll(Consumer<List<Outlet>> onSuccess,Runnable onFailure) {
        outletApiService.getAll().enqueue(new Callback<List<Outlet>>() {
            @Override
            public void onResponse(@NonNull Call<List<Outlet>> call, @NonNull Response<List<Outlet>> response) {
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
            public void onFailure(@NonNull Call<List<Outlet>> call, @NonNull Throwable t) {
                onFailure.run();
            }
        });
    }
}
