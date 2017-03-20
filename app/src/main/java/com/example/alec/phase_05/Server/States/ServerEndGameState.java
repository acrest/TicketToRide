package com.example.alec.phase_05.Server.States;

import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.EachGameState;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class ServerEndGameState implements EachGameState {
    private ServerGame game;

    public ServerEndGameState() {}

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
