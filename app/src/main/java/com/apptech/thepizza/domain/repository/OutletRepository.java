package com.apptech.thepizza.domain.repository;

import com.apptech.thepizza.domain.model.Outlet;

import java.util.List;
import java.util.function.Consumer;

public interface OutletRepository {
    void getAll(Consumer<List<Outlet>> onSuccess,Runnable onFailure);
}
