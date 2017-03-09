package com.example.alec.phase_05.Shared.model;

/**
 * Created by Alec on 2/24/17.
 */

public class Chat {
    private Integer ID;
    private String name;
    private Integer gameID;
    private String message;
    private String color;

    public Chat(String name, Integer gameID, String message, String color) {
        this.ID = null;
        this.name = name;
        this.gameID = gameID;
        this.message = message;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
