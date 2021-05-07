package com.jramons.examenandroid.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.Room.ColaboradorRoomDatabase;
import com.jramons.examenandroid.Model.Room.ColaboradoresDao;
import com.jramons.examenandroid.Model.Room.ColaboradoresEntity;
import com.jramons.examenandroid.View.ListColaboradores;

import java.util.List;

public class ListColaboradoresViewModel extends ViewModel {
    private ColaboradoresDao colaboradoresDao;
    private MutableLiveData<List<ColaboradoresEntity>> listColaboradoresLiveData;

    public ListColaboradoresViewModel(){
        colaboradoresDao = ColaboradorRoomDatabase.getInstance(InstanceApp.getContext()).getRoomDao();
    }


    public MutableLiveData<List<ColaboradoresEntity>> getListColaboradores(){
        if(listColaboradoresLiveData == null){
            listColaboradoresLiveData = new MutableLiveData<>();
        }
        listColaboradoresLiveData.setValue(colaboradoresDao.selectAllColaboradores());
        return listColaboradoresLiveData;
    }
}
