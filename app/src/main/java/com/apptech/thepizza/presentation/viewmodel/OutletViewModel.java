package com.apptech.thepizza.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apptech.thepizza.data.repository.OutletRepositoryImpl;
import com.apptech.thepizza.domain.model.Outlet;
import com.apptech.thepizza.domain.repository.OutletRepository;
import com.apptech.thepizza.util.Resource;

import java.util.List;

public class OutletViewModel extends ViewModel {
    private final OutletRepository outletRepository;
    private final MutableLiveData<Resource<List<Outlet>>> _items = new MutableLiveData<>(Resource.loading());
    public final LiveData<Resource<List<Outlet>>> items = _items;

    public OutletViewModel(){
        outletRepository = new OutletRepositoryImpl();
    }

    public void loadItems(){
        outletRepository.getAll(
                outlets -> _items.postValue(Resource.success(outlets)),
                () -> _items.postValue(Resource.failure(null))
        );
    }
}
