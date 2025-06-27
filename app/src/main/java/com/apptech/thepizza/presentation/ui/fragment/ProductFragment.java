package com.apptech.thepizza.presentation.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apptech.thepizza.data.dto.CartDto;
import com.apptech.thepizza.databinding.FragmentProductBinding;
import com.apptech.thepizza.domain.model.Product;
import com.apptech.thepizza.presentation.adapter.ProductAdapter;
import com.apptech.thepizza.presentation.viewmodel.CartViewModel;
import com.apptech.thepizza.presentation.viewmodel.ProductViewModel;
import com.apptech.thepizza.util.ObserveResource;

import java.util.List;

public class ProductFragment extends Fragment {
    private CartViewModel cartViewModel;
    private ProductAdapter productAdapter;
    private FragmentProductBinding binding;
    private ProductViewModel productViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDependencies();
        setupLoadProducts();
    }

    private void setupDependencies(){
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productAdapter = new ProductAdapter(requireContext(),id -> cartViewModel.addItem(
                                                                              new CartDto(id)));
    }

    private void setupLoadProducts(){
        setupGetCategoryIdFromArgument();
        ObserveResource.setup(
                this,
                productViewModel.items,
                this::setupOnVisibility,
                () -> binding.pb.setVisibility(View.VISIBLE),
                this::setupOnSuccess,
                () -> {}
        );
    }

    private void setupGetCategoryIdFromArgument(){
        Bundle bundle = getArguments();
        if(bundle != null){
            Integer categoryId = bundle.getInt("categoryId",0);
            productViewModel.loadItems(categoryId);
        }
    }

    private void setupOnVisibility(){
        binding.pb.setVisibility(View.GONE);
        binding.nothingToShowTV.setVisibility(View.GONE);
        binding.rv.setVisibility(View.GONE);
    }

    private void setupOnSuccess(List<Product> products){
        if(products.isEmpty()){
            binding.nothingToShowTV.setVisibility(View.VISIBLE);
            return;
        }
        productAdapter.setProducts(products);
        binding.rv.setVisibility(View.VISIBLE);
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rv.setAdapter(productAdapter);
    }
}