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
import com.miguel.myapplication.ui.Producto;
import com.miguel.myapplication.ui.ProductoViewModel;

public class CargarFragment extends Fragment {
    private EditText etCodigo, etDescripcion, etPrecio;
    private Button btnGuardar;
    private ProductoViewModel viewModel;

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

        viewModel = new ViewModelProvider(requireActivity()).get(ProductoViewModel.class);

        btnGuardar.setOnClickListener(view -> {
            String codigo = etCodigo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            if (!descripcion.isEmpty()) {
                descripcion = descripcion.substring(0, 1).toUpperCase() + descripcion.substring(1);
            }
            String precioStr = etPrecio.getText().toString().trim();

            if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(getContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double precio = Double.parseDouble(precioStr);
            Producto p = new Producto(codigo, descripcion, precio);

            if (viewModel.agregarProducto(p)) {
                Toast.makeText(getContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                etCodigo.setText("");
                etDescripcion.setText("");
                etPrecio.setText("");
            } else {
                Toast.makeText(getContext(), "Código repetido", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}