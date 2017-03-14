package com.example.alec.phase_05.Shared.model;

import com.example.alec.phase_05.Shared.model.Player;

/**
 * Created by samuel on 3/11/17.
 */

public class OtherPlayer extends AbstractPlayer {
    private int trainCardCount;
    private int destinationCardCount;

    public OtherPlayer(String name) {
        super(name);
        trainCardCount = 0;
        destinationCardCount = 0;
    }

    public OtherPlayer(IPlayer player) {
        super(player.getName());
        setColor(player.getColor());
        setTrainCount(player.getTrainCount());
        setPoints(player.getPoints());
        setLongestRoute(player.hasLongestRoute());
        trainCardCount = player.getTrainCardCount();
        destinationCardCount = player.getDestinationCardCount();
    }

    @Override
    public int getTrainCardCount() {
        return trainCardCount;
    }

    public void setTrainCardCount(int trainCardCount) {
        this.trainCardCount = trainCardCount;
    }

    @Override
    public int getDestinationCardCount() {
        return destinationCardCount;
    }

    public void setDestinationCardCount(int destinationCardCount) {
        this.destinationCardCount = destinationCardCount;
    }
}
