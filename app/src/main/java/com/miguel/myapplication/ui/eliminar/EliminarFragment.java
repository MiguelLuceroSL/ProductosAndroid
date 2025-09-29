package com.miguel.myapplication.ui.eliminar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.miguel.myapplication.R;
import com.miguel.myapplication.modelo.Producto;

public class EliminarFragment extends Fragment {

    private EditText etCodigo;
    private Button btnBuscar;
    private EliminarViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eliminar, container, false);

        etCodigo = v.findViewById(R.id.etCodigoEliminar);
        btnBuscar = v.findViewById(R.id.btnBuscar);

        viewModel = new ViewModelProvider(this).get(EliminarViewModel.class);

        viewModel.getMutableMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        viewModel.getMutableProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            Bundle bundle = new Bundle();
            bundle.putString("codigo", producto.getCodigo());
            bundle.putString("descripcion", producto.getDescripcion());
            bundle.putDouble("precio", producto.getPrecio());

            Navigation.findNavController(requireView()).navigate(R.id.nav_detalle, bundle);
        });

        btnBuscar.setOnClickListener(view -> {
            String codigo = etCodigo.getText().toString().trim();
            viewModel.buscarProducto(codigo);
        });

        return v;
    }
}