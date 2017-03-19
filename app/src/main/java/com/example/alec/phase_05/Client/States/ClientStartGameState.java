package com.example.alec.phase_05.Client.States;

import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.EachGameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/15/17.
 */

public class ClientStartGameState implements EachGameState{
    private ClientGame game;

    public ClientStartGameState() {}


    @Override
    public void drawDestinationCard(Game game, String player) {

    }

    @Override
    public boolean putBackDestinationCard(Game game, String player, DestinationCard card) {
        return false;
    }

    @Override
    public TrainCard drawTrainCardFromDeck(Game game, String player) {
        return null;
    }

    @Override
    public TrainCard pickTrainCard(Game game, String player, int cardIndex) {
        return null;
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) {

    }

    @Override
    public void endTurn(Game game, String player) {

    }
}
