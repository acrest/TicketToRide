package com.example.alec.phase_05.Shared;

import java.util.List;

public class Game {

    public String name;
    public Integer ID;
    public List<Player> currentPlayers;
    public Integer maxPlayers;

    public boolean addPlayer(Player newPlayer){
        currentPlayers.add(newPlayer);
        return true;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getCurentNumber(){
        return currentPlayers.size();
    }
}
