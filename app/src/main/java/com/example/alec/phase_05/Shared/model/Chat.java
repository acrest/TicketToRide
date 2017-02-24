package com.example.alec.phase_05.Shared.model;

/**
 * Created by Alec on 2/24/17.
 */

public class Chat {
    Integer ID;
    String name;
    Integer gameID;
    String message;

    public Chat(String name, Integer gameID, String message) {
        this.ID = null;
        this.name = name;
        this.gameID = gameID;
        this.message = message;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
