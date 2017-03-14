package com.example.alec.phase_05.Shared.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameDescription {
    private int id;
    private String name;
    private int maxPlayers;
    private List<Player> players;

    public GameDescription(int id, String name, int maxPlayers, List<IPlayer> players) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
        for (IPlayer player : players) {
            this.players.add((Player) player);
        }
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getNumberPlayers() {
        if (players == null) {
            return 0;
        }
        int count = 0;
        for (IPlayer player : players) {
            if (player != null) {
                ++count;
            }
        }
        return count;
    }

    public Set<String> getAllUsedColors() {
        Set<String> used = new HashSet<>();
        for (IPlayer player : players) {
            if (player != null) {
                used.add(player.getColor());
            }
        }
        return used;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GameDescription && id == ((GameDescription) other).id;
    }
}
