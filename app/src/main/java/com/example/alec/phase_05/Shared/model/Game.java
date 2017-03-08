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
import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public abstract class Game implements IGame {
    private int id;
    private String name;
    private int maxPlayers;
    private List<Player> players;
    private IBank bank;
    private GameMap gameMap;
    private boolean gameStarted;

    public Game(int id, String name, int maxPlayers, IBank bank, GameMap gameMap) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.bank = bank;
        this.gameMap = gameMap;
        players = new ArrayList<>();
        gameStarted = false;
    }

//    public Game(GameDescription gameDescription) {
//        this(gameDescription.getID(), gameDescription.getName(), gameDescription.getMaxPlayers(), gameDescription.getPlayers(),gameDescription.getPlayerColors());
//    }

    @Override
    public int getID() {
        return id;
    }

//    public void setID(int ID) {
//        this.ID = ID;
//    }

    public String getName() {
        return name;
    }

    @Override
    public Player getPlayer(int position) {
        if(position < players.size()) {
            return players.get(position);
        }
        return null;
    }

    @Override
    public void setPlayer(int position, Player player) {
        while(position >= players.size()) {
            players.add(null);
        }
        players.set(position, player);
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public IBank getBank() {
        return bank;
    }

    @Override
    public GameMap getMap() {
        return gameMap;
    }

    @Override
    public void setMap(GameMap map) {
        gameMap = map;
    }

    //    public void setMaxPlayers(int maxPlayers) {
//        this.maxPlayers = maxPlayers;
//    }

    @Override
    public int getNumberPlayers() {
        int count = 0;
        for(Player player : players) {
            if(player != null) {
                ++count;
            }
        }
        return count;
    }

//    public Player[] getPlayers() {
//        return players;
//    }

    @Override
    public int addPlayerAtNextPosition(Player player){
        if(hasPlayer(player))
            return -1;
        for(int i = 0; i < players.size(); ++i) {
            if(players.get(i) == null) {
                players.set(i, player);
                return i;
            }
        }
        if(players.size() < getMaxPlayers()) {
            players.add(player);
            return players.size() - 1;
        }
        return -1;
    }

    private boolean hasPlayer(Player player) {
        for(Player p : players) {
            if(player.equals(p)){
                return true;
            }
        }
        return false;
    }

//    public boolean hasPlayer(String playerName) {
//        for(Player player : players) {
//            if(player.getName().equals(playerName)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public Player getPlayerByColor(String color) {
//        for(int i = 0; i < playerColors.length; ++i) {
//            if(playerColors[i].equals(color)) {
//                return players[i];
//            }
//        }
//        return null;
//    }

//    public String getPlayerColor(Player player) {
//        for(int i = 0; i < players.length; ++i) {
//            if(players[i].equals(player)) {
//                return playerColors[i];
//            }
//        }
//        return null;
//    }

//    public void setPlayerColor(Player player, String color) {
//        for(int i = 0; i < players.length; ++i) {
//            if(players[i].equals(player)) {
//                playerColors[i] = color;
//                return;
//            }
//        }
//    }

//    public Set<String> getAllUsedColors() {
//        Set<String> used = new HashSet<>();
//        for(String color : playerColors) {
//            if(color != null) {
//                used.add(color);
//            }
//        }
//        return used;
//    }

    public GameDescription getGameDescription() {
        return new GameDescription(id, name, maxPlayers, players);
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

//    public void updateToDescription(GameDescription gameDescription) {
//        if(gameDescription != null) {
//            players = gameDescription.getPlayers();
//            playerColors = gameDescription.getPlayerColors();
//        }
//    }

    @Override
    public boolean isGameStarted() {
        return gameStarted;
    }

    @Override
    public void setGameStarted() {
        gameStarted = true;
    }
}
