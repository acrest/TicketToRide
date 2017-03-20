package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 3/2/17.
 */

public class GameInfo {
    private int id;
    private String name;
    private int maxPlayers;
    private Player[] players;
    private TrainCard[] visibleTrainCards;
    private GameMap map;

    public GameInfo(int id, String name, int maxPlayers, Player[] players, TrainCard[] visibleTrainCards, GameMap map) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.visibleTrainCards = visibleTrainCards;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }

    public TrainCard[] getVisibleTrainCards() {
        return visibleTrainCards;
    }

    public GameMap getMap() {
        return map;
    }
}
