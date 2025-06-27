package com.apptech.thepizza.domain.repository;

import com.apptech.thepizza.data.dto.CartDto;
import com.apptech.thepizza.domain.model.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public interface CartRepository {
    void getAll(Consumer<List<Cart>> onSuccess, Runnable onFailure);
    void add(CartDto cartDto);
    void getTotal(Consumer<BigDecimal> onSuccess);
    void delete(Integer id,Runnable onSuccess);
    void deleteAll();
}
