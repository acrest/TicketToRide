package com.example.alec.phase_05.Shared;

/**
 * Created by Alec on 2/5/17.
 */
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
