package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.GameDescription;

import java.util.List;

/**
 * Created by samuel on 2/14/17.
 */

public class GameDescriptionHolder {
    private List<GameDescription> gameDescriptions;

    public GameDescriptionHolder(List<GameDescription> gameDescriptions) {
        this.gameDescriptions = gameDescriptions;
    }

    public List<GameDescription> getGameDescriptions() {
        return gameDescriptions;
    }

    public void setGameDescriptions(List<GameDescription> gameDescriptions) {
        this.gameDescriptions = gameDescriptions;
    }
}
