package com.android.ecare.ecare;

/**
 * Created by TALHA on 22.5.15.
 */
public class Patient {
    String name;
    String email;
    String password;
    int age;
    String phoneNo;
    String sex;
    int id;

    public Patient(int id, String name, String email, String password, int age, String phoneNo, String sex) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNo = phoneNo;
        this.sex = sex;
        this.id = id;
    }

    public Patient(String name, String email, String password, int age, String phoneNo, String sex) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNo = phoneNo;
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getSex() {
        return sex;
    }






}
