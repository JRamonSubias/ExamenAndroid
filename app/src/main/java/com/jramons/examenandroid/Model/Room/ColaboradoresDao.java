package com.jramons.examenandroid.Model.Room;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ColaboradoresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertColaborador(ColaboradoresEntity colaboradoresEntity);

    @Query("SELECT * FROM colaborador")
    List<ColaboradoresEntity> selectAllColaboradores(); 
}
