package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class DrawDestinationState implements GameState {
    private ClientGame state = null;
    private Facade facade;


    public DrawDestinationState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void drawDestinationCards() throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void putBackDestinationCards(DestinationCard[] cards) throws StateWarning {
        System.out.println("Putting back destination card(s).");
        facade.putBackDestinationCards(cards);
        state.setTurnState(new ReturnDestinationState(state, 1));
    }

    @Override
    public void claimRoute(int routeId, TrainType type) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void endTurn() throws StateWarning {
        facade.finishTurn();
        state.setTurnState(new EndTurnState(state));
    }

    @Override
    public String toString() {
        return "Drawn Destination Cards";
    }
}
