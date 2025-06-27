package com.apptech.thepizza.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apptech.thepizza.data.repository.ProductRepositoryImpl;
import com.apptech.thepizza.domain.model.Product;
import com.apptech.thepizza.domain.repository.ProductRepository;
import com.apptech.thepizza.util.Resource;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<Resource<List<Product>>> _items = new MutableLiveData<>(Resource.loading());
    public final LiveData<Resource<List<Product>>> items = _items;

    public ProductViewModel(){
        productRepository = new ProductRepositoryImpl();
    }

    public void loadItems(Integer categoryId){
        productRepository.getByCategoryId(
                categoryId,
                products -> _items.setValue(Resource.success(products)),
                () -> _items.postValue(Resource.failure(null)));
    }
}
