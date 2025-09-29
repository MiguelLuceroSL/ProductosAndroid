package com.miguel.myapplication.repository;

import com.miguel.myapplication.MainActivity;
import com.miguel.myapplication.modelo.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class ProductoRepository {

    public ArrayList<Producto> getProductos() {
        return MainActivity.productosEstaticos;
    }

    public boolean agregarProducto(Producto p) {
        for (Producto prod : MainActivity.productosEstaticos) {
            if (prod.getCodigo().equals(p.getCodigo())) {
                return false;
            }
        }
        MainActivity.productosEstaticos.add(p);
        Collections.sort(MainActivity.productosEstaticos,
                Comparator.comparing(Producto::getDescripcion));
        return true;
    }
}