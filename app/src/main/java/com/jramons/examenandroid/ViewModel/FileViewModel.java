package com.jramons.examenandroid.ViewModel;


import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.StorageReference;
import com.jramons.examenandroid.InstanceApp;
import com.jramons.examenandroid.Model.FileProvider;
import com.jramons.examenandroid.Model.Retrofit.Resutl;
import com.jramons.examenandroid.Model.Room.ColaboradorRoomDatabase;
import com.jramons.examenandroid.Model.Room.ColaboradoresDao;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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



}
