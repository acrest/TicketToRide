package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class OneDrawnCardState implements GameState {

    private ClientGame state = null;
    private Facade facade;

    public OneDrawnCardState(ClientGame drawCardState) {
        facade = Facade.getInstance();
        state = drawCardState;
    }


    @Override
    public void drawTrainCardFromDeck(String player) throws StateWarning  {
        //draw a train card.
        System.out.println("Train card drawn from deck.");
        state.setTurnState(new TwoDrawnCardState(state));
    }

    @Override
    public void pickTrainCard(String player, int cardIndex) throws StateWarning {
// pick train card
        System.out.println("Train card picked from face up cards.");
        // check not rainbow card.
        facade.pickTrainCard(cardIndex);
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            throw new StateWarning("Cannot draw a rainbow card if you already have picked a card.");
        } else {
            state.setTurnState(new OneDrawnCardState(state));
        }
    }

    @Override
    public void drawDestinationCard(String player) throws StateWarning {
        throw new StateWarning("You must get another Train card.");
    }

    @Override
    public void putBackDestinationCard(String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("You must get another Train card.");
    }

    @Override
    public void claimRoute(String player, int routeId) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void endTurn(String player) throws StateWarning {
        throw new StateWarning("Cannot finish turn yet. You must get another Train card.");
    }
}
