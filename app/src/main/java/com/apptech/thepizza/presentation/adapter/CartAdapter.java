package com.apptech.thepizza.presentation.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apptech.thepizza.R;
import com.apptech.thepizza.databinding.ActivityCartItemBinding;
import com.apptech.thepizza.domain.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private Consumer<Integer> onClicked;
    private List<Cart> carts = new ArrayList<>();

    public CartAdapter(Context context,Consumer<Integer> onClicked){
        this.context = context;
        this.onClicked = onClicked;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        ActivityCartItemBinding binding;
        public CartViewHolder(ActivityCartItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityCartItemBinding binding = ActivityCartItemBinding.inflate(LayoutInflater.from(parent
                                                          .getContext()),parent,false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Picasso.get().load(cart.getProduct().getImageUrl()).into(holder.binding.productIV);
        holder.binding.productNameTV.setText(cart.getProduct().getName());
        holder.binding.productPriceTV.setText(context.getString(R.string.one_string_format,cart.getProduct()
                                                                                              .getPrice()));
        holder.binding.pcsTV.setText(context.getString(R.string.pcs_format,cart.getQuantity()));
        holder.binding.deleteIV.setOnClickListener(v -> onClicked.accept(cart.getId()));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
