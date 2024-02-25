package com.kmt.javaAuthDemo.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;

    private Role role;


    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}