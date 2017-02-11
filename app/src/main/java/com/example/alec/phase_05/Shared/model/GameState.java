package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by samuel on 2/9/17.
 */

public class GameState {
    private int ID;
    private String name;
    private int maxPlayers;
    private Map<String, Player> playerColors;

    public GameState(int id, String name, int maxPlayers) {
        ID = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        playerColors = new HashMap<>();
    }

    public GameState(GameDescription gameDescription) {
        this(gameDescription.getID(), gameDescription.getName(), gameDescription.getMaxPlayers());
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

    public Collection<Player> getCurrentPlayers() {
        return playerColors.values();
    }

    public boolean addPlayer(Player newPlayer, String color){
        if(playerColors.containsKey(color))
            return false;
        playerColors.put(color, newPlayer);
        return true;
    }

    public boolean hasPlayer(Player player) {
        return playerColors.containsValue(player);
    }

    public boolean hasPlayer(String playerName) {
        return playerColors.containsKey(playerName);
    }

    public Player getPlayerByColor(String color) {
        if(!playerColors.containsKey(color))
            return null;
        return playerColors.get(color);
    }

    public void setPlayerColor(Player player, String color) {
        playerColors.put(color, player);
    }

    public Set<String> getAllUsedColors() {
        return playerColors.keySet();
    }

    public GameDescription getGameDescription() {
        //TODO: set up the gameDesciption's player list
        GameDescription gameDescription = new GameDescription(ID, name, maxPlayers);
        return gameDescription;
    }

}
