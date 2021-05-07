package com.jramons.examenandroid.Model.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.annotations.NotNull;

@Entity(tableName = "colaborador")
public class ColaboradoresEntity {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int idColaborador;

    @NotNull
    private String name;
    @NotNull
    private String lastName;

    @NotNull
    private Double latitud;

    @NotNull
    private Double longitud;

    public ColaboradoresEntity( String name, String lastName, Double latitud, Double longitud) {
        this.idColaborador = idColaborador;
        this.name = name;
        this.lastName = lastName;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
