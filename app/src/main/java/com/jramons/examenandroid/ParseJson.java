package com.jramons.examenandroid;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.jramons.examenandroid.Model.Data;
import com.jramons.examenandroid.Model.Employees;
import com.jramons.examenandroid.Model.Location;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseJson {

    public List<Employees> readJsonStream(InputStream in) throws IOException{
        JsonReader reader = new JsonReader(new InputStreamReader(in,"UTF-8"));
        try {
            reader.beginObject();
            return readColaboradoresArray(reader);

        }finally {
            reader.close();
        }
    }

    private List<Employees> readColaboradoresArray(JsonReader reader) throws IOException {
        List<Employees> employees = new ArrayList<>();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("data")) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                               String emp = reader.nextName();
                               if(emp.equals("employees")){
                                    reader.beginArray();
                                    while (reader.hasNext()){
                                      employees.add(readColaborador(reader));
                                    }
                               }
                    }
                }
            }
        reader.endArray();
        return employees;
    }

    private Employees readColaborador(JsonReader reader) throws IOException {
        int id = 0 ;
        String name = null;
        String email = null;
        Location location1 = new Location();

        reader.beginObject();
        while (reader.hasNext()){
            String nombre = reader.nextName();
            if(nombre.equals("id")){
                id = reader.nextInt();
            }else if(nombre.equals("name")){
                name = reader.nextString();
            }else if(nombre.equals("location")){
                reader.beginObject();
                while (reader.hasNext()){
                   String loc = reader.nextName();
                   if(loc.equals("lat")){
                       location1.setLat(reader.nextDouble());
                   }else if (loc.equals("log")){
                       location1.setLog(reader.nextDouble());
                   }
                }
                reader.endObject();
            }else if(nombre.equals("mail")){
                email = reader.nextString();
            }
            else{
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Employees(id,name,location1,email);
    }
}
