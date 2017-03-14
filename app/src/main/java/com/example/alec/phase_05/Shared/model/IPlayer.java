package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 3/13/17.
 */

public interface IPlayer {
    String getName();

    String getColor();

    void setColor(String color);

    int getTrainCardCount();

    int getDestinationCardCount();

    int getTrainCount();

    void setTrainCount(int count);

    int getPoints();

    void setPoints(int points);

    void setLongestRoute(boolean longestRoute);

    boolean hasLongestRoute();
}
