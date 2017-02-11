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
    private GameDescription gameDescription;
    private Map<String, Player> playerColors;

    public GameState(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
        playerColors = new HashMap<>();
    }

    public GameDescription getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
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
        return playerColors.containsKey(player);
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
}
