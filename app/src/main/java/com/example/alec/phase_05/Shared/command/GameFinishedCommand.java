package com.example.alec.phase_05.Shared.command;

import java.util.Map;

/**
 * Created by samuel on 3/27/17.
 */

public abstract class GameFinishedCommand extends BaseCommand {
    private Map<String, Integer> bonusPoints;

    public GameFinishedCommand(Map<String, Integer> bonusPoints) {
        super("GameFinished");
        this.bonusPoints = bonusPoints;
    }

    public Map<String, Integer> getBonusPoints() {
        return bonusPoints;
    }
}
