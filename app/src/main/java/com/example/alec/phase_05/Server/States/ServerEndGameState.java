package com.example.alec.phase_05.Server.States;

import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.EachGameState;
<<<<<<< HEAD
import com.example.alec.phase_05.Shared.model.Game;

=======
>>>>>>> 8a5a18c48201cd391d3573892c77359647375430
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public class ServerEndGameState implements EachGameState {
    private ServerGame game;

    public ServerEndGameState() {}


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
