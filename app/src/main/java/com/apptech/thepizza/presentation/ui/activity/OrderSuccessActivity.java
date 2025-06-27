package com.apptech.thepizza.presentation.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.apptech.thepizza.databinding.ActivityOrderSuccessBinding;
import com.apptech.thepizza.util.NavigationUtil;

public class OrderSuccessActivity extends AppCompatActivity {
    private ActivityOrderSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView(){
        binding.viewOrderTV.setOnClickListener(v -> {});
        binding.closeTV.setOnClickListener(v -> {
            NavigationUtil.navigateTo(this,MainActivity.class,null);
            finish();
        });
    }
}