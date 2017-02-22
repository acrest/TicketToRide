package com.example.alec.phase_05.Shared.model;

import com.example.alec.phase_05.Server.CommandManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by samuel on 2/9/17.
 */

public class Game {
    private int ID;
    private String name;
    private int maxPlayers;
    private Player[] players;
    private String[] playerColors;
    private CommandManager commandManager;

    public Game(int id, String name, int maxPlayers, Player[] players, String[] playerColors) {
        ID = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.playerColors = playerColors;
    }

    public Game(GameDescription gameDescription) {
        this(gameDescription.getID(), gameDescription.getName(), gameDescription.getMaxPlayers(), gameDescription.getPlayers(),gameDescription.getPlayerColors());
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
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

    public Player[] getPlayers() {
        return players;
    }

    public boolean addPlayer(Player newPlayer){
        if(hasPlayer(newPlayer))
            return false;
        for(int i = 0; i < players.length; ++i) {
            if(players[i] == null) {
                players[i] = newPlayer;
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayer(Player player) {
        for(Player p : players) {
            if(player.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayer(String playerName) {
        for(Player player : players) {
            if(player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    public Player getPlayerByColor(String color) {
        for(int i = 0; i < playerColors.length; ++i) {
            if(playerColors[i].equals(color)) {
                return players[i];
            }
        }
        return null;
    }

    public String getPlayerColor(Player player) {
        for(int i = 0; i < players.length; ++i) {
            if(players[i].equals(player)) {
                return playerColors[i];
            }
        }
        return null;
    }

    public void setPlayerColor(Player player, String color) {
        for(int i = 0; i < players.length; ++i) {
            if(players[i].equals(player)) {
                playerColors[i] = color;
                return;
            }
        }
    }

    public Set<String> getAllUsedColors() {
        Set<String> used = new HashSet<>();
        for(String color : playerColors) {
            if(color != null) {
                used.add(color);
            }
        }
        return used;
    }

    public GameDescription getGameDescription() {
        return new GameDescription(ID, name, maxPlayers, players, playerColors);
    }

    public void updateToDescription(GameDescription gameDescription) {
        if(gameDescription != null) {
            players = gameDescription.getPlayers();
            playerColors = gameDescription.getPlayerColors();
        }
    }
}
