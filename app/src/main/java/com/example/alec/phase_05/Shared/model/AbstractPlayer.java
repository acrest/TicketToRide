package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 3/13/17.
 */

public abstract class AbstractPlayer implements IPlayer {
    private String name;
    private String color;
    private int trainCount;
    private int points;
    private boolean longestRoute;

    public AbstractPlayer(String name) {
        this.name = name;
        color = null;
        trainCount = 45;
        points = 0;
        longestRoute = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int getTrainCount() {
        return trainCount;
    }

    @Override
    public void setTrainCount(int count) {
        trainCount = count;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void setLongestRoute(boolean longestRoute) {
        this.longestRoute = longestRoute;
    }

    @Override
    public boolean hasLongestRoute() {
        return longestRoute;
    }
}
