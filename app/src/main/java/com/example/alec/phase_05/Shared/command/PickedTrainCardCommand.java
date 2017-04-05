package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/11/17.
 */

public abstract class PickedTrainCardCommand extends BaseCommand {
    private String playerName;
    // New visible cards resulting from the last pick.
    // Most of the time would only be one different from the client,
    // but could potentially be all different in the case of too many wild cards.
    private TrainCard[] visibleCards;
    private int remainingCards;

    public PickedTrainCardCommand(String playerName, TrainCard[] visibleCards, int remainingCards) {
        super("PickedTrainCard");
        this.playerName = playerName;
        this.visibleCards = visibleCards;
        this.remainingCards = remainingCards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public TrainCard[] getVisibleCards() {
        return visibleCards;
    }
}
