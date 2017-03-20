package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;

/**
 * Created by samuel on 3/13/17.
 */

public class PlayerStat {
    private String playerName;
    private int points;
    private int trainCount;
    private int trainCards;
    private int destinationCards;
    private String color;

    public PlayerStat(String playerName, String color) {
        this.playerName = playerName;
        this.color = color;
    }

    public PlayerStat(IPlayer player) {
        playerName = player.getName();
        points = player.getPoints();
        trainCount = player.getTrainCount();
        trainCards = player.getTrainCardCount();
        destinationCards = player.getDestinationCardCount();
        color = player.getColor();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(int trainCount) {
        this.trainCount = trainCount;
    }

    public int getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(int trainCards) {
        this.trainCards = trainCards;
    }

    public int getDestinationCards() {
        return destinationCards;
    }

    public void setDestinationCards(int destinationCards) {
        this.destinationCards = destinationCards;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
