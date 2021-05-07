package com.jramons.examenandroid.Model;

import com.jramons.examenandroid.Model.Retrofit.Resutl;
import com.jramons.examenandroid.Model.Retrofit.RetroClient;
import com.jramons.examenandroid.Model.Retrofit.RetroService;

import java.io.File;

import retrofit2.Call;

public class FileProvider {
    private static FileProvider instance;

    public static FileProvider getInstance(){
        if(instance== null){
            instance = new FileProvider();
        }
        return instance;
    }

    public Call<Resutl> getFile(){
        return RetroClient.getClientObject().create(RetroService.class).getFile();
    }
}
