package com.jramons.examenandroid.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.Room.ColaboradoresEntity;
import com.jramons.examenandroid.R;
import com.jramons.examenandroid.ViewModel.ListColaboradoresViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListColaboradores extends Fragment {
    private ListView lvListColaboradores;
    private List<String> list; 
    private ArrayAdapter<String> listAdapter; 
    private ListColaboradoresViewModel viewModel;
    private List<ColaboradoresEntity> entityList;

    public ListColaboradores() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ListColaboradoresViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllColaboradores();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_colaboradores, container, false);
        initUi(view);


        lvListColaboradores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.mapsColaboradores);
            }
        });
        return view; 
    }

    private void getAllColaboradores() {
        if(list == null){
            list = new ArrayList<>();
        }
        viewModel.getListColaboradores().observe(this, colaboradoresEntities -> {
            entityList = colaboradoresEntities;

            for (ColaboradoresEntity Listcolaborador: colaboradoresEntities) {
                String colaborador =
                        Listcolaborador.getName()+"\n"+
                                Listcolaborador.getLastName()+"\n"+
                                Listcolaborador.getLatitud()+"\n"+
                                Listcolaborador.getLongitud();
                list.add(colaborador);
            }
            listAdapter= new ArrayAdapter<>(InstanceApp.getContext(), android.R.layout.simple_list_item_1,list);
            lvListColaboradores.setAdapter(listAdapter);
        });
    }

    private void initUi(View view) {
        lvListColaboradores = view.findViewById(R.id.listViewColaborares);
    }
}