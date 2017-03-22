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

public class TwoDrawnCardState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public TwoDrawnCardState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck(Game game, String player) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void drawDestinationCard(Game game, String player) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) throws StateWarning {
        throw new StateWarning("Already drew card. You must end your turn now.");
    }

    @Override
    public void endTurn(Game game, String player) {
        System.out.println("Turn ended.");
        facade.finishTurn();
        state.setTurnState(state.getEndTurnState());
    }
}