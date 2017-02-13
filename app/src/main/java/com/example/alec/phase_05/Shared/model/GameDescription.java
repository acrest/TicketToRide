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
    private List<Player> players;
    private Map<Player, String> playerColors;

    public GameDescription(int ID, String name, int maxPlayers, List<Player> players, Map<Player, String> playerColors) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Map<Player, String> getPlayerColors() {
        return playerColors;
    }

    public void setPlayerColors(Map<Player, String> playerColors) {
        this.playerColors = playerColors;
    }

    public Collection<String> getAllUsedColors() {
        return playerColors.values();
    }

    public boolean equals(Object other) {
        if(other instanceof GameDescription) {
            return getID() == ((GameDescription) other).getID();
        }
        return false;
    }
}
