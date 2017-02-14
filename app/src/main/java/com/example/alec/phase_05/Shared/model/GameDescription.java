package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameDescription {
    private int ID;
    private String name;
    private int maxPlayers;
    private Player[] players;
    private String[] playerColors;

    public GameDescription(int ID, String name, int maxPlayers, Player[] players, String[] playerColors) {
        this.ID = ID;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.playerColors = playerColors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String[] getPlayerColors() {
        return playerColors;
    }

    public void setPlayerColors(String[] playerColors) {
        this.playerColors = playerColors;
    }

    public int getNumberPlayers() {
        int count = 0;
        for(Player player : players) {
            if(player != null) {
                ++count;
            }
        }
        return count;
    }

    public boolean equals(Object other) {
        if(other instanceof GameDescription) {
            return getID() == ((GameDescription) other).getID();
        }
        return false;
    }
}
