package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by samuel on 2/25/17.
 */

public abstract class DrawTrainCardCommand extends GameCommand {
    public DrawTrainCardCommand(String playerName, int gameID) {
        super("DrawTrainCard", playerName, gameID);
    }
}
