package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public abstract class DrawnTrainCardCommand extends BaseCommand {
    private String playerName;

    public DrawnTrainCardCommand(String playerName) {
        super("DrawnTrainCard");
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
