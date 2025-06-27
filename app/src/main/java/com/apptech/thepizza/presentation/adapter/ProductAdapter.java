package com.apptech.thepizza.presentation.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apptech.thepizza.R;
import com.apptech.thepizza.databinding.FragmentProductItemBinding;
import com.apptech.thepizza.domain.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private Consumer<Integer> onClicked;
    private List<Product> products = new ArrayList<>();

    public ProductAdapter(Context context,Consumer<Integer> onClicked){
        this.context = context;
        this.onClicked = onClicked;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        FragmentProductItemBinding binding;
        public ProductViewHolder(FragmentProductItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentProductItemBinding binding = FragmentProductItemBinding.inflate(LayoutInflater.from(parent
                                                               .getContext()),parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        Picasso.get().load(product.getImageUrl()).into(holder.binding.productIV);
        holder.binding.productNameTV.setText(product.getName());
        holder.binding.productPriceTV.setText(context.getString(R.string.one_string_format,product
                                                                                    .getPrice()));
        holder.binding.addToCartIV.setOnClickListener(v -> onClicked.accept(product.getId()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
