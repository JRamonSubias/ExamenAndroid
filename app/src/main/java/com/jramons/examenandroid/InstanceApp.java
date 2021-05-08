package com.jramons.examenandroid;

import android.app.Application;
import android.content.Context;

public class InstanceApp extends Application {

    public static final String URL = "https://dl.dropboxusercontent.com/s/";
    public static final String APP_SETTING_FILE ="EXAMEN_ANDROID";
    public static final String FILE_JSON ="employees_json";
    private static InstanceApp instance;

    public static InstanceApp getInstance(){return instance;}

    public static Context getContext(){return instance;}

    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();
    }
}
