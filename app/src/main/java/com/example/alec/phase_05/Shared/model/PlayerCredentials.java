package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 3/13/17.
 */

public class PlayerCredentials implements java.io.Serializable {
    private String username;
    private String password;

    public PlayerCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
