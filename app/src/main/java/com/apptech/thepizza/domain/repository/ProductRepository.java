package com.apptech.thepizza.domain.repository;

import com.apptech.thepizza.domain.model.Product;

import java.util.List;
import java.util.function.Consumer;

public interface ProductRepository {
    void getByCategoryId(Integer categoryId, Consumer<List<Product>> onSuccess,Runnable onFailure);
}
