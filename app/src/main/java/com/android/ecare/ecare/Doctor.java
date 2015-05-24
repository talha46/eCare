package com.android.ecare.ecare;

/**
 * Created by TALHA on 22.5.15.
 */
public class Doctor {
    int id;
    String name;
    String email;
    String password;

    public Doctor(int id, String name, String password, String email, String specallization) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.specallization = specallization;
    }

    public Doctor(String name, String email, String password, String specallization) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.specallization = specallization;
    }

    public String getSpecallization() {
        return specallization;
    }

    public void setSpecallization(String specallization) {
        this.specallization = specallization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String specallization;
}
