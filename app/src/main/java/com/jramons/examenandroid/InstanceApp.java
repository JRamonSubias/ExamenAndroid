package com.jramons.examenandroid;

import android.app.Application;
import android.content.Context;

public class InstanceApp extends Application {

    public static final String URL = "https://dl.dropboxusercontent.com/s/";
    private static InstanceApp instance;

    public static InstanceApp getInstance(){return instance;}

    public static Context getContext(){return instance;}

    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();
    }
}
