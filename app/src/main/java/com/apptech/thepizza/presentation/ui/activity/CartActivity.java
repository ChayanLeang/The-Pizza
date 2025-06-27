package com.apptech.thepizza.presentation.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apptech.thepizza.R;
import com.apptech.thepizza.databinding.ActivityCartBinding;
import com.apptech.thepizza.domain.model.Cart;
import com.apptech.thepizza.presentation.adapter.CartAdapter;
import com.apptech.thepizza.presentation.viewmodel.CartViewModel;
import com.apptech.thepizza.util.NavigationUtil;
import com.apptech.thepizza.util.ObserveResource;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private CartViewModel cartViewModel;
    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupDependency();
        initView();
        setupLoadCarts();
        setupLoadTotal();
    }

    private void setupDependency(){
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartAdapter = new CartAdapter(this,id -> cartViewModel.deleteItem(id));
    }

    private void initView(){
        binding.mtb.setNavigationOnClickListener(v -> finish());
        binding.placeOrderTV.setOnClickListener(v -> NavigationUtil.navigateTo(this,
                                                          PlaceOrderActivity.class,null));
    }

    private void setupLoadCarts(){
        cartViewModel.loadItems();
        ObserveResource.setup(
                this,
                cartViewModel.items,
                this::setupOnVisibility,
                () -> binding.pb.setVisibility(View.VISIBLE),
                this::setupOnSuccess,
                () -> binding.cartEmptyTV.setVisibility(View.VISIBLE)
        );
    }

    private void setupOnVisibility(){
        binding.pb.setVisibility(View.GONE);
        binding.cartEmptyTV.setVisibility(View.GONE);
        binding.rv.setVisibility(View.GONE);
    }

    private void setupOnSuccess(List<Cart> carts){
        if(carts.isEmpty()){
            binding.cartEmptyTV.setVisibility(View.VISIBLE);
            return;
        }
        cartAdapter.setCarts(carts);
        binding.rv.setVisibility(View.VISIBLE);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(cartAdapter);
    }

    private void setupLoadTotal(){
        cartViewModel.loadTotal();
        cartViewModel.total.observe(this,total -> binding.totalTV.setText(getString(R
                                                                    .string.one_string_format,total)));
    }
}