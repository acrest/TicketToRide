package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samuel on 2/9/17.
 */

public class GameState {
    private int ID;
    private String name;
    private int maxPlayers;
    private List<Player> players;
    private Map<Player, String> playerColors;

    public GameState(int id, String name, int maxPlayers, List<Player> playerList, Map<Player,String> playerMap) {
        ID = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = playerList;
        playerColors = playerMap;
    }

    public GameState(GameDescription gameDescription) {
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
        return players.size();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean addPlayer(Player newPlayer){
        if(hasPlayer(newPlayer))
            return false;
        players.add(newPlayer);
        return true;
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public boolean hasPlayer(String playerName) {
        return players.contains(playerName);
    }

    public Player getPlayerByColor(String color) {
        for(Map.Entry<Player, String> entry : playerColors.entrySet()) {
            if(entry.getValue().equals(color))
                return entry.getKey();
        }
        return null;
    }

    public String getPlayerColor(Player player) {
        return playerColors.get(player);
    }

    public void setPlayerColor(Player player, String color) {
        playerColors.put(player, color);
    }

    public Collection<String> getAllUsedColors() {
        return playerColors.values();
    }

    public GameDescription getGameDescription() {
        return new GameDescription(ID, name, maxPlayers,
                Collections.unmodifiableList(players),
                Collections.unmodifiableMap(playerColors));
    }
}
