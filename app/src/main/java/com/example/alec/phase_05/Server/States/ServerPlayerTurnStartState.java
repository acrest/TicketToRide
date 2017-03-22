package com.example.alec.phase_05.Server.States;

import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class ServerPlayerTurnStartState implements GameState {
    private boolean lastTurn;
    private ServerGame game;

    public ServerPlayerTurnStartState() {
        lastTurn = false;
    }


    @Override
    public void drawDestinationCard(Game game, String player) {

    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) {

    }

    @Override
    public void drawTrainCardFromDeck(Game game, String player) {

    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) {

    }

    @Override
    public void claimRoute(Game game, String player, int routeId) {

    }

    @Override
    public void endTurn(Game game, String player) {

    }
}
