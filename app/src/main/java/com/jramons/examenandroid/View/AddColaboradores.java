package com.jramons.examenandroid.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.AddColaboradorViewModel;


public class AddColaboradores extends Fragment {
    private TextInputLayout etName, etLastname, etLaittud, etLongitud;
    private Button btnAddColaborador;
    private String name,lastname,latitud,longitud;
    private AddColaboradorViewModel viewModel;

    public AddColaboradores() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddColaboradorViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_colaboradores, container, false);
        initUI(view);
        btnAddColaborador.setOnClickListener(v -> {
            validateData();
        });

        return view;
    }

    private void validateData() {
        etName.setError(null);
        etLastname.setError(null);
        etLaittud.setError(null);
        etLongitud.setError(null);

        name = etName.getEditText().getText().toString();
        lastname = etLastname.getEditText().getText().toString();
        latitud = etLaittud.getEditText().getText().toString();
        longitud = etLongitud.getEditText().getText().toString();

        if(name.isEmpty()){
            etName.setError("Ingrese un nombre de usuario");
        }else if(lastname.isEmpty()){
            etLastname.setError("Ingrese apellido");
        }else if (latitud.isEmpty()){
            etLongitud.setError("ingrese la Latitud");
        }else if(longitud.isEmpty()){
            etLongitud.setError("Ingrese la Longitud");
        }else{
            addColaborador(name,lastname,latitud,longitud);
        }

    }

    private void addColaborador(String name, String lastname, String latitud, String longitud) {
        viewModel.InsertColaborador(name,lastname,latitud,longitud).observe(getActivity(), insert ->{
            if(insert){
                Toast.makeText(InstanceApp.getContext(), "Colaborador Agregado", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void initUI(View view) {
        etName = view.findViewById(R.id.editTextName);
        etLastname = view.findViewById(R.id.editTextLastName);
        etLaittud = view.findViewById(R.id.editTextLatitud);
        etLongitud = view.findViewById(R.id.editTextLongitud);
        btnAddColaborador = view.findViewById(R.id.buttonAddColaborador);
    }
}