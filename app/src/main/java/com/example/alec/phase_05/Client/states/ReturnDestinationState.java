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

public class ReturnDestinationState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public ReturnDestinationState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }


    @Override
    public void drawTrainCardFromDeck(Game game, String player) throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void drawDestinationCard(Game game, String player) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void endTurn(Game game, String player) throws StateWarning {
        System.out.println("Cards returned. Ending turn.");
        facade.finishTurn();
        state.setTurnState((state.getEndTurnState()));

    }
}
