package com.apptech.thepizza.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apptech.thepizza.data.dto.CartDto;
import com.apptech.thepizza.data.repository.CartRepositoryImpl;
import com.apptech.thepizza.domain.model.Cart;
import com.apptech.thepizza.domain.repository.CartRepository;
import com.apptech.thepizza.util.Resource;

import java.math.BigDecimal;
import java.util.List;

public class CartViewModel extends ViewModel {
    private final CartRepository cartRepository;
    private final MutableLiveData<Resource<List<Cart>>> _items = new MutableLiveData<>(Resource.loading());
    public final LiveData<Resource<List<Cart>>> items = _items;
    private final MutableLiveData<BigDecimal> _total = new MutableLiveData<>();
    public final LiveData<BigDecimal> total = _total;

    public CartViewModel(){
        cartRepository = new CartRepositoryImpl();
    }

    public void loadItems(){
        cartRepository.getAll(
                carts -> _items.setValue(Resource.success(carts)),
                () -> _items.setValue(Resource.failure(null)));
    }

    public void addItem(CartDto cartDto){
        cartRepository.add(cartDto);
    }

    public void loadTotal(){
        cartRepository.getTotal(_total::setValue);
    }

    public void deleteItem(Integer id){
        cartRepository.delete(id, this::loadItems);
    }

    public void deleteAll(){
        cartRepository.deleteAll();
    }
}
