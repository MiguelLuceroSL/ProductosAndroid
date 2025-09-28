package com.miguel.myapplication.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.myapplication.R;
import com.miguel.myapplication.ui.ProductoAdapter;
import com.miguel.myapplication.ui.ProductoViewModel;

import java.util.ArrayList;

public class ListarFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private ProductoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listar, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProductoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(ProductoViewModel.class);

        viewModel.getMutableProductos().observe(getViewLifecycleOwner(), productos -> {
            adapter.updateList(productos);
        });

        return v;
    }
}