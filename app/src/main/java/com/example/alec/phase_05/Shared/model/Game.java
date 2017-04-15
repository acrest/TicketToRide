package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2/9/17.
 */

public abstract class Game implements IGame, Serializable {
    private int id;
    private String name;
    private int maxPlayers;
    private List<IPlayer> players;
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

    @Override
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public IPlayer getPlayer(int position) {
        if (position < players.size()) {
            return players.get(position);
        }
        return null;
    }

    @Override
    public IPlayer getPlayerByName(String playerName) {
        for(IPlayer player : players) {
            if(player != null && player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void setPlayer(int position, IPlayer player) {
        while (position >= players.size()) {
            players.add(null);
        }
        players.set(position, player);
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public int getNumberPlayers() {
        int count = 0;
        for (IPlayer player : players) {
            if (player != null) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public TrainCard getVisibleCard(int index) {
        return bank.getVisibleCard(index);
    }

    @Override
    public int getNumberOfTrainCards() {
        return bank.getNumberOfTrainCards();
    }

    public int getNumberOfDestinationCards() {
        return bank.getNumberOfDestinationCards();
    }

    @Override
    public City getCityByName(String name) {
        return gameMap.getCityByName(name);
    }

    @Override
    public void addCity(City city) {
        gameMap.addCity(city);
    }

    @Override
    public Route getRouteByID(int routeID) {
        return gameMap.getRouteByID(routeID);
    }

    @Override
    public void addRoute(Route route) {
        gameMap.addRoute(route);
    }

    @Override
    public int addPlayerAtNextPosition(IPlayer player) {
        if (hasPlayer(player))
            return -1;
        for (int i = 0; i < players.size(); ++i) {
            if (players.get(i) == null) {
                players.set(i, player);
                return i;
            }
        }
        if (players.size() < getMaxPlayers()) {
            players.add(player);
            return players.size() - 1;
        }
        return -1;
    }

    private boolean hasPlayer(IPlayer player) {
        for (IPlayer p : players) {
            if (player.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public GameDescription getGameDescription() {
        return new GameDescription(id, name, maxPlayers, players);
    }

    public void setPlayers(List<IPlayer> players) {
        this.players = players;
    }

    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public boolean isGameStarted() {
        return gameStarted;
    }

    @Override
    public void setGameStarted() {
        gameStarted = true;
    }

    protected IBank getBank() {
        return bank;
    }

    protected GameMap getGameMap() {
        return gameMap;
    }

    protected void setGameMap(GameMap map) {
        gameMap = map;
    }

}
