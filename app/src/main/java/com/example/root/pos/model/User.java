package com.example.root.pos.model;

public class User {

    public String Name;
    public String username;
    public String address;
    public String password;
    public String confirmPassword;
    public String phonenumber;
    public String occupationnname;


    public User(String name, String username, String address, String password, String confirmPassword, String phonenumber, String occupationnname) {
        Name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phonenumber = phonenumber;
        this.occupationnname = occupationnname;
    }
}
