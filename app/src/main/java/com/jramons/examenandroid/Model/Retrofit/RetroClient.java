package com.jramons.examenandroid.Model.Retrofit;

import androidx.recyclerview.widget.RecyclerView;

import com.jramons.examenandroid.InstanceApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static Retrofit getClientObject(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InstanceApp.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
