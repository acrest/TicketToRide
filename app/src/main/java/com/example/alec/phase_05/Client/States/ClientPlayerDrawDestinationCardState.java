package com.example.alec.phase_05.Client.States;

import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.EachGameState;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/15/17.
 */

public class ClientPlayerDrawDestinationCardState implements EachGameState {
    private boolean lastTurn;
    private ClientGame game;

    public ClientPlayerDrawDestinationCardState() {
        lastTurn = false;
    }


    @Override
    public DestinationCard drawDestinationCard(String player) {
        return null;
    }

    @Override
    public boolean putBackDestinationCard(String player, DestinationCard card) {
        return false;
    }

    @Override
    public TrainCard drawTrainCardFromDeck(String player) {
        return null;
    }

    @Override
    public TrainCard pickTrainCard(String player, int cardIndex) {
        return null;
    }

    @Override
    public void claimRoute(String player, int routeId) {

    }

    @Override
    public void endTurn(String player) {

    }
}
