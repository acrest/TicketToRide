package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 2/23/17.
 */

public class Route {

    private City city1, city2;
    private int length;
    private Player owner;
    private String color;

    public Route(City city1, City city2, int length, Player owner, String color) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.owner = owner;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
