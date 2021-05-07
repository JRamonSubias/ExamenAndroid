package com.jramons.examenandroid.Model.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroService {


    @GET("5u21281sca8gj94/getFile.json?dl=0")
    Call<Resutl> getFile();
}
