package com.miguel.myapplication.ui.cargar;

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

import com.miguel.myapplication.R;

public class CargarFragment extends Fragment {

    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnGuardar;
    private CargarViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cargar, container, false);

        etCodigo = v.findViewById(R.id.etCodigo);
        etDescripcion = v.findViewById(R.id.etDescripcion);
        etPrecio = v.findViewById(R.id.etPrecio);
        btnGuardar = v.findViewById(R.id.btnGuardar);

        viewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        viewModel.getMutableMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        viewModel.getMutableLimpiarCampos().observe(getViewLifecycleOwner(), unused -> {
            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");
        });

        btnGuardar.setOnClickListener(view -> {
            String codigo = etCodigo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String precioStr = etPrecio.getText().toString().trim();

            viewModel.agregarProducto(codigo, descripcion, precioStr);
        });

        return v;
    }
}