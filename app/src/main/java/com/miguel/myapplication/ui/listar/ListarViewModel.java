package com.miguel.myapplication.ui.listar;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.miguel.myapplication.modelo.Producto;
import com.miguel.myapplication.repository.ProductoRepository;
import java.util.ArrayList;
public class ListarViewModel extends AndroidViewModel {
    private ProductoRepository repo = new ProductoRepository();
    private MutableLiveData<ArrayList<Producto>> mutableProductos;
    public ListarViewModel(@NonNull Application application) {
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
}