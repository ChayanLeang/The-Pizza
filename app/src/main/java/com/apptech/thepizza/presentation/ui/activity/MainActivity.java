package com.apptech.thepizza.presentation.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.apptech.thepizza.R;
import com.apptech.thepizza.databinding.ActivityMainBinding;
import com.apptech.thepizza.presentation.ui.fragment.OutletFragment;
import com.apptech.thepizza.presentation.ui.fragment.ProductFragment;
import com.apptech.thepizza.presentation.viewmodel.CartViewModel;
import com.apptech.thepizza.util.NavigationUtil;

public class MainActivity extends AppCompatActivity {
    private CartViewModel cartViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupDependency();
        initView();
        setupNavigationView();
        setupLoadTotal();
    }

    private void setupDependency(){
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }

    private void initView(){
        binding.mtb.setNavigationOnClickListener(v -> binding.dl.openDrawer(GravityCompat.START));
        binding.myCartTV.setOnClickListener(v ->  NavigationUtil.navigateTo(this,
                                                             CartActivity.class,null));
    }

    private void setupNavigationView() {
        navigateTo("Home",12345678);
        binding.home.setOnClickListener(v -> navigateTo("Home",12345678));
        binding.pizza.setOnClickListener(v -> navigateTo("Pizza",23456789));
        binding.puff.setOnClickListener(v -> navigateTo("Puff",34567891));
        binding.appetizer.setOnClickListener(v -> navigateTo("Appetizer",45678912));
        binding.pasta.setOnClickListener(v -> navigateTo("Pasta",56789123));
        binding.salad.setOnClickListener(v -> navigateTo("Salad",67891234));
        binding.beverage.setOnClickListener(v -> navigateTo("Beverage",78912345));
        binding.outlet.setOnClickListener(v -> navigateTo("Outlet",null));
    }

    private void navigateTo(String title,Integer categoryId) {
        binding.mtb.setTitle(title);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(categoryId != null){
            Bundle bundle = new Bundle();
            bundle.putInt("categoryId",categoryId);
            ProductFragment productFragment = new ProductFragment();
            productFragment.setArguments(bundle);
            transaction.replace(R.id.fcv, productFragment);
        }
        else{
            OutletFragment outletFragment = new OutletFragment();
            transaction.replace(R.id.fcv,outletFragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
        binding.dl.closeDrawers();
    }

    private void setupLoadTotal(){
        cartViewModel.loadTotal();
        cartViewModel.total.observe(this,total -> binding.totalTV.setText(getString(R
                                                                    .string.one_string_format,total)));
    }
}