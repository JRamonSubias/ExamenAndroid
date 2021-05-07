package com.jramons.examenandroid.Model.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ColaboradoresEntity.class},version = 1,exportSchema = false)
public abstract class ColaboradorRoomDatabase extends RoomDatabase {
    private static final String DB_NAME="db_colaborador";
    private static ColaboradorRoomDatabase instance;

    public static ColaboradorRoomDatabase getInstance(Context context){
        if(instance == null){
            synchronized (ColaboradorRoomDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ColaboradorRoomDatabase.class,DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

    public abstract ColaboradoresDao getRoomDao();

}
