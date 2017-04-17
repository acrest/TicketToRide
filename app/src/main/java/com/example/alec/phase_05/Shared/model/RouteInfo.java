package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 4/17/17.
 */

public class RouteInfo {
    private City city1, city2;
    private int length;
    private String owner;
    private TrainType type;
    private int id;
    private int twinID;

    public RouteInfo(City city1, City city2, int length, String owner, TrainType type, int id, int twinID) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.owner = owner;
        this.type = type;
        this.id = id;
        this.twinID = twinID;
    }

    public RouteInfo(Route route) {
        city1 = route.getCity1();
        city2 = route.getCity2();
        length = route.getLength();
        owner = route.getOwner() == null ? null : route.getOwner().getName();
        type = route.getType();
        id = route.getId();
        twinID = route.getTwinID();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public TrainType getType() {
        return type;
    }

    public void setType(TrainType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTwinID(){
        return twinID;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RouteInfo) {
            return id == ((RouteInfo) obj).id;
        }
        return false;
    }
}
