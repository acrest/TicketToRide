package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/11/17.
 */

public abstract class PickTrainCardCommand extends GameCommand {
    private int cardIndex;

    public PickTrainCardCommand(String playerName, int gameID, int cardIndex) {
        super("PickTrainCard", playerName, gameID);
        this.cardIndex = cardIndex;
    }

    public int getCardIndex() {
        return cardIndex;
    }
}
