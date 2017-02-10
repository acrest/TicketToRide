package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public class GameState {
    private GameDescription gameDescription;
    private List<Player> currentPlayers;

    public GameState(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
        currentPlayers = new ArrayList<>();
    }

    public GameDescription getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public boolean addPlayer(Player newPlayer){
        if(currentPlayers.contains(newPlayer))
            return false;
        currentPlayers.add(newPlayer);
        return true;
    }

    public boolean hasPlayer(Player player) {
        return currentPlayers.contains(player);
    }

    public boolean hasPlayer(String playerName) {
        return currentPlayers.contains(playerName);
    }
}
