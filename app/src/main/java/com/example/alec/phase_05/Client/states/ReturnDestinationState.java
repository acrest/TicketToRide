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
    private int cardsReturned;

    public ReturnDestinationState(ClientGame playerTurnStates, int cardsReturned) {
        state = playerTurnStates;
        facade = Facade.getInstance();
        this.cardsReturned=cardsReturned;
    }


    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void drawDestinationCard() throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        if(cardsReturned == 2)
            throw new StateWarning("Attempt to return too many destination cards.");
        facade.putBackDestinationCard(card);
        cardsReturned++;
    }

    @Override
    public void claimRoute(int routeId) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void endTurn() throws StateWarning {
        System.out.println("Cards returned. Ending turn.");
        facade.finishTurn();
        state.setTurnState(new EndTurnState(state));

    }
}
