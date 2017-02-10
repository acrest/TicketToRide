package com.example.alec.phase_05.Shared;

public class Player {

    private String name;
    private Integer ID;
    private String password;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }




}
