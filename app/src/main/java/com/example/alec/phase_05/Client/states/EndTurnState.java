package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class EndTurnState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public EndTurnState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck(Game game, String player) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void drawDestinationCard(Game game, String player) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void endTurn(Game game, String player) throws StateWarning {
        System.out.println("Turn is already ended, but will end again.");
        facade.finishTurn();
        state.setTurnState(state.getEndTurnState());
    }
}
