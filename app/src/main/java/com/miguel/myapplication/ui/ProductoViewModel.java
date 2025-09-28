package com.miguel.myapplication.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ProductoViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Producto>> mutableProductos;
    private ProductoRepository repo = new ProductoRepository();

    public ProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Producto>> getMutableProductos() {
        if (mutableProductos == null) {
            mutableProductos = new MutableLiveData<>();
            cargarProductos();
        }
        return mutableProductos;
    }

    public void cargarProductos() {
        if (mutableProductos == null) {
            mutableProductos = new MutableLiveData<>();
        }
        mutableProductos.setValue(repo.getProductos());
    }

    public boolean agregarProducto(Producto p) {
        boolean agregado = repo.agregarProducto(p);
        if (mutableProductos == null) {
            mutableProductos = new MutableLiveData<>();
        }
        mutableProductos.setValue(repo.getProductos());
        return agregado;
    }
}
