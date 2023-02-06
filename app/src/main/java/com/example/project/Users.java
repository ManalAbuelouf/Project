package com.example.project;

import java.util.ArrayList;

public class Users {
    public static ArrayList<Users> userssArrayList=new ArrayList<Users>();
    private String email;
    private String FName;
    private String LName;
    private String password;
    private String PContinent;

    public Users() {
    }

    public Users(String email, String FName, String LName, String password, String PContinent) {
        this.email = email;
        this.FName = FName;
        this.LName = LName;
        this.password = password;
        this.PContinent = PContinent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPContinent() {
        return PContinent;
    }

    public void setPContinent(String PContinent) {
        this.PContinent = PContinent;
    }

    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", FName='" + FName + '\'' +
                ", LName='" + LName + '\'' +
                ", password='" + password + '\'' +
                ", PContinent='" + PContinent + '\'' +
                '}';
    }

}