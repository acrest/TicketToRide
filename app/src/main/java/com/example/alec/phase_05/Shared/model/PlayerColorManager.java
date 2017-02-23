package com.example.alec.phase_05.Shared.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by samuel on 2/22/17.
 */

public class PlayerColorManager implements IPlayerColorManager {
    private Map<Player, String> playerColors;

    public PlayerColorManager() {
        playerColors = new HashMap<>();
    }

    @Override
    public String getColor(Player player) {
        if(playerColors.containsKey(player)) {
            return playerColors.get(player);
        }
        return null;
    }

    @Override
    public void setColor(Player player, String color) {
        playerColors.put(player, color);
    }

    @Override
    public Collection<String> getUsedColors() {
        return playerColors.values();
    }
}
