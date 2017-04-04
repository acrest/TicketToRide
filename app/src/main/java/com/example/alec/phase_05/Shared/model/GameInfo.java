package com.example.alec.phase_05.Shared.model;

import java.util.Map;

/**
 * Created by samuel on 3/2/17.
 */

public class GameInfo {
    private int id;
    private String name;
    private int maxPlayers;
    private Player[] players;
    private TrainCard[] visibleTrainCards;
    private int trainCardsRemaining;
    private int destinationCardsRemaining;
    private GameMap map;


    public GameInfo(int id, String name, int maxPlayers, Player[] players, TrainCard[] visibleTrainCards, int trainCardsRemaining, int destinationCardsRemaining, GameMap map) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.visibleTrainCards = visibleTrainCards;
        this.trainCardsRemaining = trainCardsRemaining;
        this.destinationCardsRemaining = destinationCardsRemaining;
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


    public int getTrainCardsRemaining() {
        return trainCardsRemaining;
    }

    public int getDestinationCardsRemaining() {
        return destinationCardsRemaining;
    }
}
