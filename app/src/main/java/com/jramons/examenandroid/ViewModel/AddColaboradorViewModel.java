package com.jramons.examenandroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.Room.ColaboradorRoomDatabase;
import com.jramons.examenandroid.Model.Room.ColaboradoresDao;
import com.jramons.examenandroid.Model.Room.ColaboradoresEntity;


public class AddColaboradorViewModel extends ViewModel {
    private ColaboradoresDao colaboradoresDao;
    private MutableLiveData<Boolean> checkAddColaborador;


    public AddColaboradorViewModel(){
        colaboradoresDao = ColaboradorRoomDatabase.getInstance(InstanceApp.getContext()).getRoomDao();
    }

    public MutableLiveData<Boolean> InsertColaborador(String name, String lastame, String latitud, String longitud){
        if(checkAddColaborador == null){
            checkAddColaborador = new MutableLiveData<>();
        }
        Double dLatitud = Double.parseDouble(latitud);
        Double dLongitud = Double.parseDouble(longitud);

        colaboradoresDao.insertColaborador(new ColaboradoresEntity(name,lastame,dLatitud,dLongitud));

        return checkAddColaborador;
    }

}
