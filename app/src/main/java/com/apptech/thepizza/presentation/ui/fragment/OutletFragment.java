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

import com.apptech.thepizza.databinding.FragmentOutletBinding;
import com.apptech.thepizza.domain.model.Outlet;
import com.apptech.thepizza.presentation.adapter.OutletAdapter;
import com.apptech.thepizza.presentation.viewmodel.OutletViewModel;
import com.apptech.thepizza.util.ObserveResource;

import java.util.List;

public class OutletFragment extends Fragment {
    private OutletAdapter outletAdapter;
    private FragmentOutletBinding binding;
    private OutletViewModel outletViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOutletBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDependencies();
        setupLoadProducts();
    }

    private void setupDependencies(){
        outletAdapter = new OutletAdapter();
        outletViewModel = new ViewModelProvider(this).get(OutletViewModel.class);
    }

    private void setupLoadProducts(){
        outletViewModel.loadItems();
        ObserveResource.setup(
                this,
                outletViewModel.items,
                this::setupOnVisibility,
                () -> binding.pb.setVisibility(View.VISIBLE),
                this::setupOnSuccess,
                () -> {}
        );
    }

    private void setupOnVisibility(){
        binding.pb.setVisibility(View.GONE);
        binding.nothingToShowTV.setVisibility(View.GONE);
        binding.rv.setVisibility(View.GONE);
    }

    private void setupOnSuccess(List<Outlet> outlets){
        if(outlets.isEmpty()){
            binding.nothingToShowTV.setVisibility(View.VISIBLE);
            return;
        }
        outletAdapter.setOutlets(outlets);
        binding.rv.setVisibility(View.VISIBLE);
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rv.setAdapter(outletAdapter);
    }
}