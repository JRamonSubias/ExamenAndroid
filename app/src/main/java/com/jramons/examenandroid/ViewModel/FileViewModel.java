package com.jramons.examenandroid.ViewModel;



import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.StorageReference;
import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.Employees;
import com.jramons.examenandroid.Model.Providers.FDBProvider;
import com.jramons.examenandroid.Model.Providers.FileProvider;
import com.jramons.examenandroid.Model.Retrofit.Resutl;
import com.jramons.examenandroid.Model.Room.ColaboradorRoomDatabase;
import com.jramons.examenandroid.Model.Room.ColaboradoresDao;
import com.jramons.examenandroid.Model.Room.ColaboradoresEntity;
import com.jramons.examenandroid.ParseJson;
import com.jramons.examenandroid.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FileViewModel extends ViewModel {

    private FileProvider mFile;
    private ColaboradoresDao colaboradoresDao;
    private StorageReference mStorage;
    private File file;
    private  long dowloadId;
    private MutableLiveData<String> urlFile;


    private List<Employees> re;
    private ParseJson parse;

    public FileViewModel(){
        mFile = FileProvider.getInstance();
        colaboradoresDao = ColaboradorRoomDatabase.getInstance(InstanceApp.getContext()).getRoomDao();
    }

    public MutableLiveData<String> getFile(){
        if( urlFile == null){
            urlFile = new MutableLiveData<>();
        }
        mFile.getFile().enqueue(new Callback<Resutl>() {
            @Override
            public void onResponse(Call<Resutl> call, Response<Resutl> response) {
                if(response.isSuccessful()){
                    urlFile.setValue(response.body().getData().getFile());
                }
            }

            @Override
            public void onFailure(Call<Resutl> call, Throwable t) {

            }
        });

        return urlFile;
    }


    public void getColaboradoresFileJson() {
        InputStream is = InstanceApp.getContext().getResources().openRawResource(R.raw.employees_data);
        try {
            parse = new ParseJson();
            re = parse.readJsonStream(is);
            Log.i("[JSON]","Lectura Json terminada");
            for (Employees employ: re) {
                colaboradoresDao.insertColaborador(new ColaboradoresEntity(employ.getName(),employ.getMail(),employ.getLocation().getLat(),employ.getLocation().getLog()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void respaldoFDB(){
        List<ColaboradoresEntity> colaboradoresEntities = colaboradoresDao.selectAllColaboradores();
        Map<String,String> map = new HashMap<>();
        for (ColaboradoresEntity col: colaboradoresEntities
             ) {
            map.put("id",String.valueOf(col.getIdColaborador()));
            map.put("name",col.getName());
            map.put("email",col.getLastName());
            map.put("latitud",String.valueOf(col.getLatitud()));
            map.put("longitud",String.valueOf(col.getLongitud()));
        FDBProvider.taskEmployee().child(String.valueOf(col.getIdColaborador())).setValue(map).addOnCompleteListener(task -> {
            Log.i("[FIREBASE]", "Ingresado a la base de datos");
        });
        }


    }



}
