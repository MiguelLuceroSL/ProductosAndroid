package com.miguel.myapplication.ui;

import android.util.Log;

import com.miguel.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductoRepository {
    private static ArrayList<Producto> productos = new ArrayList<>();

    public ArrayList<Producto> getProductos() {
        Collections.sort(productos, Comparator.comparing(Producto::getDescripcion));
        return productos;
    }

    public boolean agregarProducto(Producto p) {
        for (Producto prod : productos) {
            if (prod.getCodigo().equals(p.getCodigo())) {
                return false; //codigo repetido
            }
        }
        productos.add(p);
        MainActivity.productosEstaticos.add(p);
        return true;
    }
}
