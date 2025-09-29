package com.miguel.myapplication.ui.detalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.miguel.myapplication.R;

public class DetalleFragment extends Fragment {

    private TextView tvCodigo, tvDescripcion, tvPrecio;
    private Button btnEliminar, btnVolver;
    private DetalleViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        tvCodigo = v.findViewById(R.id.tvCodigo);
        tvDescripcion = v.findViewById(R.id.tvDescripcion);
        tvPrecio = v.findViewById(R.id.tvPrecio);
        btnEliminar = v.findViewById(R.id.btnEliminar);
        btnVolver = v.findViewById(R.id.btnVolver);

        viewModel = new ViewModelProvider(this).get(DetalleViewModel.class);

        viewModel.getMutableMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        viewModel.getMutableVolverAlListar().observe(getViewLifecycleOwner(), volver -> {
                NavOptions options = new NavOptions.Builder()
                        .setPopUpTo(R.id.nav_listar, false)
                        .build();
                Navigation.findNavController(v).navigate(R.id.nav_listar, null, options);

        });

        Bundle args = getArguments();
        String codigo = args.getString("codigo");
        String descripcion = args.getString("descripcion");
        double precio = args.getDouble("precio");

        tvCodigo.setText("Código: " + codigo);
        tvDescripcion.setText("Descripción: " + descripcion);
        tvPrecio.setText("Precio: $" + precio);

        btnEliminar.setOnClickListener(view -> {
            viewModel.eliminarProducto(codigo);
        });

        btnVolver.setOnClickListener(view -> {
            NavOptions options = new NavOptions.Builder()
                    .setPopUpTo(R.id.nav_listar, false)
                    .build();
            Navigation.findNavController(v).navigate(R.id.nav_listar, null, options);
        });

        return v;
    }
}