package com.jramons.examenandroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data{

    @SerializedName("employees")
    private List<Employees> employees = new ArrayList<Employees>();

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

}