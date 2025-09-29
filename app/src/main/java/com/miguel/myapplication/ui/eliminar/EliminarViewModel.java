package com.miguel.myapplication.ui.eliminar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.miguel.myapplication.modelo.Producto;
import com.miguel.myapplication.repository.ProductoRepository;

public class EliminarViewModel extends AndroidViewModel {
    private final ProductoRepository repo = new ProductoRepository();

    private MutableLiveData<Producto> mutableProductoEncontrado;
    private MutableLiveData<String> mutableMensaje;

    public EliminarViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Producto> getMutableProductoEncontrado() {
        if (mutableProductoEncontrado == null) {
            mutableProductoEncontrado = new MutableLiveData<>();
        }
        return mutableProductoEncontrado;
    }

    public MutableLiveData<String> getMutableMensaje() {
        if (mutableMensaje == null) {
            mutableMensaje = new MutableLiveData<>();
        }
        return mutableMensaje;
    }

    public void buscarProducto(String codigo) {
        for (Producto p : repo.getProductos()) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                getMutableProductoEncontrado().setValue(p);
                return;
            }
        }
        getMutableMensaje().setValue("Producto no encontrado");
    }
}