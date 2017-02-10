package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.List;

public class GameDescription {
    private Integer ID;
    private String name;
    private Integer maxPlayers;

    public GameDescription(int ID, String name, int maxPlayers) {
        this.ID = ID;
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean equals(Object other) {
        if(other instanceof GameDescription) {
            return getID() == ((GameDescription) other).getID();
        }
        return false;
    }
}
