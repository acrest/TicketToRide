package com.example.alec.phase_05.Shared.model;

import java.util.List;

/**
 * Created by samuel on 3/2/17.
 */

public class GameState {

    private int id;
    private String name;
    private int maxPlayers;
    private List<Player> players;
    private List<TrainCard> visibleTrainCards;

    public GameState(int id, String name, int maxPlayers, List<Player> players, List<TrainCard> visibleTrainCards) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = players;
        this.visibleTrainCards = visibleTrainCards;
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

    public List<Player> getPlayers() {
        return players;
    }

    public List<TrainCard> getVisibleTrainCards() {
        return visibleTrainCards;
    }
}
