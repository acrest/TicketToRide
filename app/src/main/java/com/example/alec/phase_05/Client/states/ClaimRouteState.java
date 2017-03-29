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

public class ClaimRouteState implements GameState {
    private Facade facade;
    private ClientGame state = null;

    public ClaimRouteState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        throw new StateWarning("You claimed a route. You must end your turn.");
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        throw new StateWarning("You claimed a route. You must end your turn.");
    }

    @Override
    public void drawDestinationCard() throws StateWarning {
        throw new StateWarning("You claimed a route. You must end your turn.");
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        throw new StateWarning("You claimed a route. You must end your turn.");
    }

    @Override
    public void claimRoute(int routeId) throws StateWarning {
        throw new StateWarning("You already claimed a route. You must end your turn.");
    }

    @Override
    public void endTurn() throws StateWarning {
        System.out.println("Ending turn.");
        facade.finishTurn();

        state.setTurnState((new EndTurnState(state)));
    }

    @Override
    public String toString() {
        return "Claimed Route";
    }
}
