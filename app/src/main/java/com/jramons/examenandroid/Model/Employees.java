package com.jramons.examenandroid.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employees {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("mail")
    @Expose
    private String mail;

    public Employees(Integer id, String name, Location location, String mail) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
