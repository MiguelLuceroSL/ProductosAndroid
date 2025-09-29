package com.miguel.myapplication.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SalirFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Salir")
                .setMessage("¿Desea cerrar la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> getActivity().finish())
                .setNegativeButton("No", null)
                .show();

        return new View(getContext());
    }
}