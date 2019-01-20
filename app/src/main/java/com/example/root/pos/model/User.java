package com.example.root.pos.model;

public class User {

    public String Name;
    public String userName;
    public String address;
    public String password;
    public String confirmPassword;


    public User(String name, String userName, String address, String password, String confirmPassword) {
        Name = name;
        this.userName = userName;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
