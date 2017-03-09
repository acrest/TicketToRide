package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;

public class Player {
    private static int PlayerId = 0;
    private int id;
    private String name;
    private String password;
    private int trainCount;
    private int pointCount;
    boolean longestRoute;
    private String color;
    ArrayList<TrainCard> trainCards = new ArrayList<>();
    ArrayList<DestinationCard> destinationCards = new ArrayList<>();
    private boolean host;

    public Player(String name, String password) {
        this.id = PlayerId;
        PlayerId++;
        this.name = name;
        this.password = password;
        trainCount = 45;
        pointCount = 0;
        longestRoute = false;
        color = null;
        host = false;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void addTrainCard(TrainCard trainCard)
    {
        trainCards.add(trainCard);
    }

    public ArrayList<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public void addDestinationCard(DestinationCard destinationCard) { destinationCards.add(destinationCard);}

    public int getTrainCount() { return trainCount;}

    public void setTrainCount(int count) {
        trainCount = count;
    }

    public int getPointCount() { return pointCount;}

    public void addPoints(int points) { pointCount += points;}

    public void removePoints(int points) { pointCount -= points;}

    public void setLongestRoute(boolean longestRoute) { this.longestRoute = longestRoute;}

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Player) {
            return getName().equals(((Player) other).getName());
        } else if(other instanceof String) {
            return getName().equals((String) other);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}


/*+getTrainCardNumber() : Int
        +getInitialDestinationCards():Array<DestinationCard>
+returnInitialDestCard(DestCard dc): void
        +getDestinationCard():DesinationCard*/
