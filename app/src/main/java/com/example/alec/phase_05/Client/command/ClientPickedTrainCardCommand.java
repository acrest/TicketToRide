package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.PickedTrainCardCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/11/17.
 */

public class ClientPickedTrainCardCommand extends PickedTrainCardCommand {
    public ClientPickedTrainCardCommand(String playerName, int cardIndex, TrainCard nextCard, int remainingCards) {
        super(playerName, cardIndex, nextCard, remainingCards);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().pickTrainCard(getPlayerName(), getCardIndex(), getNextCard(), getRemainingCards());
        return null;
    }
}
