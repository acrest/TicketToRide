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

public class OnePickedCardState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public OnePickedCardState(ClientGame playTurnStates) {

        state = playTurnStates;
        facade = Facade.getInstance();
    }


    @Override
    public void drawTrainCardFromDeck(Game game, String player) {
        // draw train card
        System.out.println("another card added from the deck");
        facade.drawTrainCard();
        state.setTurnState(state.getOneDrawnOnePickedCardState());

    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) {
        System.out.println("Another card picked from the face up cards.");
        // pick train card
        // check not rainbow card.
        facade.pickTrainCard(cardIndex);
        state.setTurnState(state.getOneDrawnOnePickedCardState());

    }

    @Override
    public void drawDestinationCard(Game game, String player) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void endTurn(Game game, String player) throws StateWarning {
        throw new StateWarning("Cannot end turn now. You must get another Train card.");
    }
}
