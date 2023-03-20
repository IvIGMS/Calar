/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.logic;

/**
 *
 * @author ivanfriasgil
 */
public class User {
    private String email;
    private String name;
    private String surName;
    private String password;
    
    public User(String email, String name, String surName, String password){
        this.email = email;
        this.name = name;
        this.surName = surName;
        this.password = password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getName(){
        return name;
    }
    
    public String getSurName(){
        return surName;
    }
    
    public String getPassword(){
        return password;
    }
}
