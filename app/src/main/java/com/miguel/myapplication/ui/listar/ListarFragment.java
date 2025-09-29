package com.miguel.myapplication.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel.myapplication.R;
import com.miguel.myapplication.modelo.Producto;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListarAdapter adapter;
    private ListarViewModel viewModel;
    private TextView emptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listar, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        emptyView   = v.findViewById(R.id.emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListarAdapter(new ArrayList<Producto>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        viewModel.getMutableProductos().observe(getViewLifecycleOwner(), productos -> {
            adapter.updateList(productos);
            if (productos.isEmpty()) {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                emptyView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        return v;
    }
}