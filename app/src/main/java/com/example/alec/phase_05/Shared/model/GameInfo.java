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
    private int playerTurnIndex;
    private PlayerTurnStatus playerStatus;


    public GameInfo(int id, String name, int maxPlayers, Player[] players, TrainCard[] visibleTrainCards, int trainCardsRemaining, int destinationCardsRemaining, GameMap map, int gamePlayerIndex, PlayerTurnStatus playerStatus) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.visibleTrainCards = visibleTrainCards;
        this.trainCardsRemaining = trainCardsRemaining;
        this.destinationCardsRemaining = destinationCardsRemaining;
        this.map = map;
        playerTurnIndex = gamePlayerIndex;
        this.playerStatus = playerStatus;
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

    public void setPlayerTurnIndex(int index) {
        index = index % getMaxPlayers();
    }

    public int getPlayerTurnIndex() {
        return playerTurnIndex;
    }

    public void setPlayerTurnStatus(PlayerTurnStatus status) {
        playerStatus = status;
    }

    public PlayerTurnStatus getPlayerTurnStatus() {
        return playerStatus;
    }

}
