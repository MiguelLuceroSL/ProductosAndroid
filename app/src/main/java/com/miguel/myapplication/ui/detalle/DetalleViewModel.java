package com.miguel.myapplication.ui.detalle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miguel.myapplication.R;
import com.miguel.myapplication.repository.ProductoRepository;

public class DetalleViewModel extends AndroidViewModel {
    private final ProductoRepository repo = new ProductoRepository();

    private MutableLiveData<String> mutableMensaje;
    private MutableLiveData<Boolean> mutableVolverAlListar;

    public DetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getMutableMensaje() {
        if (mutableMensaje == null) {
            mutableMensaje = new MutableLiveData<>();
        }
        return mutableMensaje;
    }

    public MutableLiveData<Boolean> getMutableVolverAlListar() {
        if (mutableVolverAlListar == null) {
            mutableVolverAlListar = new MutableLiveData<>();
        }
        return mutableVolverAlListar;
    }

    public void eliminarProducto(String codigo) {
        repo.getProductos().removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
        getMutableMensaje().setValue("Producto eliminado");
        getMutableVolverAlListar().setValue(true);
    }
}