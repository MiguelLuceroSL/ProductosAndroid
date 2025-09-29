package com.miguel.myapplication.ui.cargar;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.miguel.myapplication.modelo.Producto;
import com.miguel.myapplication.repository.ProductoRepository;
public class CargarViewModel extends AndroidViewModel {
    private ProductoRepository repo = new ProductoRepository();
    private MutableLiveData<String> mutableMensaje;
    public CargarViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<String> getMutableMensaje() {
        if (mutableMensaje == null) {
            mutableMensaje = new MutableLiveData<>();
        }
        return mutableMensaje;
    }
    public void agregarProducto(String codigo, String descripcion, String precioStr) {
        if (codigo.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            mutableMensaje.setValue("Complete todos los campos");
            return;
        }
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch(NumberFormatException e) {
            mutableMensaje.setValue("Precio inválido");
            return;
        }
        descripcion = descripcion.substring(0,1).toUpperCase() + descripcion.substring(1);
        Producto p = new Producto(codigo, descripcion, precio);
        if (repo.agregarProducto(p)) {
            mutableMensaje.setValue("Producto agregado");
        } else {
            mutableMensaje.setValue("Código repetido");
        }
    }
}