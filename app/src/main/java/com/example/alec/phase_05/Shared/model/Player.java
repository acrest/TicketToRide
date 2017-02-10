package com.example.alec.phase_05.Shared.model;

public class Player {
    private String name;
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

    public boolean equals(Object other) {
        if(other instanceof Player) {
            return getName().equals(((Player) other).getName());
        } else if(other instanceof String) {
            return getName().equals((String) other);
        }
        return false;
    }
}
