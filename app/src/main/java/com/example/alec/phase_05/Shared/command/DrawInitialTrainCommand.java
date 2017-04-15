package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by Alec on 4/15/17.
 */

public abstract class DrawInitialTrainCommand extends GameCommand{
    public DrawInitialTrainCommand(String playerName, int gameID) {
        super("DrawInitialTrainCard", playerName, gameID);
    }
}
