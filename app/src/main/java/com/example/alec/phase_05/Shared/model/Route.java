package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 2/23/17.
 */

public class Route {

    private City city1, city2;
    private int length;
    private Player owner;
    private TrainType type;

    public Route(City city1, City city2, int length, Player owner, TrainType type) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.owner = owner;
        this.type = type;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }

    public int getPoints() {
        switch(length) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 4;
            case 4: return 7;
            case 5: return 10;
            case 6: return 15;
        }
        return 0;
    }
}
