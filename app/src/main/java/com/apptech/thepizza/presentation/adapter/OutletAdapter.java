package com.apptech.thepizza.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apptech.thepizza.databinding.FragmentOutletItemBinding;
import com.apptech.thepizza.domain.model.Outlet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OutletAdapter extends RecyclerView.Adapter<OutletAdapter.OutletViewHolder> {
    private List<Outlet> outlets = new ArrayList<>();

    public void setOutlets(List<Outlet> outlets) {
        this.outlets = outlets;
    }

    public static class OutletViewHolder extends RecyclerView.ViewHolder{
        FragmentOutletItemBinding binding;
        public OutletViewHolder(FragmentOutletItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public OutletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentOutletItemBinding binding = FragmentOutletItemBinding.inflate(LayoutInflater.from(parent
                                                             .getContext()),parent,false);
        return new OutletViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OutletViewHolder holder, int position) {
        Outlet outlet = outlets.get(position);
        Picasso.get().load(outlet.getImageUrl()).into(holder.binding.outletIV);
        holder.binding.outletNameTV.setText(outlet.getName());
        holder.binding.addressTV.setText(outlet.getAddress());
    }

    @Override
    public int getItemCount() {
        return outlets.size();
    }
}
