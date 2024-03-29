package com.jramons.examenandroid;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    private SharedPreferenceManager(){
    }

    private static SharedPreferences getSharedPreference(){
        return InstanceApp.getContext().getSharedPreferences(InstanceApp.APP_SETTING_FILE, Context.MODE_PRIVATE);
    }

    public static void setSomeStringValue(String dataLabel,String dataValue){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(dataLabel,dataValue);
        editor.commit();
    }

    public static  String getSomeStringValue (String dataLabel){
        return getSharedPreference().getString(dataLabel,null);
    }

    public static void setSomeBooleanValue(String dataLabel,Boolean dataValue){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(dataLabel,dataValue);
        editor.commit();
    }

    public static boolean getSomeBooleanValue (String dataLabel){
        return getSharedPreference().getBoolean(dataLabel,false);
    }

    public static void ClearPreference(){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.clear();
        editor.commit();
    }

    public static void DeleteValue(String value){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.remove(value);
        editor.commit();
    }

}
