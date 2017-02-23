package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameDescription {
    private int id;
    private String name;
    private int maxPlayers;
    private List<Player> players;

    public GameDescription(int id, String name, int maxPlayers, List<Player> players) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
//        this.numPlayers = numPlayers;
//        this.players = players;
//        this.playerColors = playerColors;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public int getID() {
        return id;
    }

//    public void setID(Integer id) {
//        this.id = id;
//    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

//    public void setMaxPlayers(Integer maxPlayers) {
//        this.maxPlayers = maxPlayers;
//    }

//    public Player[] getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(Player[] players) {
//        this.players = players;
//    }
//
//    public String[] getPlayerColors() {
//        return playerColors;
//    }
//
//    public void setPlayerColors(String[] playerColors) {
//        this.playerColors = playerColors;
//    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNumberPlayers() {
        if(players == null) {
            return 0;
        }
        int count = 0;
        for(Player player : players) {
            if(player != null) {
                ++count;
            }
        }
        return count;
    }

    public Set<String> getAllUsedColors() {
        Set<String> used = new HashSet<>();
        for(Player player : players) {
            if(player != null) {
                used.add(player.getColor());
            }
        }
        return used;
    }

    public boolean equals(Object other) {
        if(other instanceof GameDescription) {
            return getID() == ((GameDescription) other).getID();
        }
        return false;
    }
}
