package com.apptech.thepizza.presentation.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.apptech.thepizza.R;
import com.apptech.thepizza.databinding.ActivityPlaceOrderBinding;
import com.apptech.thepizza.presentation.viewmodel.CartViewModel;
import com.apptech.thepizza.util.NavigationUtil;

public class PlaceOrderActivity extends AppCompatActivity {
    private CartViewModel cartViewModel;
    private ActivityPlaceOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupDependency();
        initView();
        setupLoadTotal();
    }

    private void setupDependency(){
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }

    private void initView(){
        setupValidation();
        binding.mtb.setNavigationOnClickListener(v -> finish());
        binding.orderTV.setOnClickListener(v -> {
            cartViewModel.deleteAll();
            NavigationUtil.navigateTo(this,OrderSuccessActivity.class,null);
            finish();
        });
    }

    private void setupValidation(){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String address = binding.addressTIET.getText().toString();
                boolean enable = !TextUtils.isEmpty(address);
                binding.orderTV.setEnabled(enable);
                binding.orderTV.setAlpha(enable ? 1f : 0.5f);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        binding.addressTIET.addTextChangedListener(textWatcher);
    }

    private void setupLoadTotal(){
        cartViewModel.loadTotal();
        cartViewModel.total.observe(this,total -> binding.totalTV.setText(getString(R
                                                                    .string.one_string_format,total)));
    }
}