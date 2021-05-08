package com.jramons.examenandroid.Model.Providers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FDBProvider {

    public static DatabaseReference getInstance(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference taskEmployee(){
        return FDBProvider.getInstance().child("Employee");
    }


}
