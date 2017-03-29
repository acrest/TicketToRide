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
    public void drawTrainCardFromDeck() throws StateWarning  {
        //draw a train card.
        System.out.println("Train card drawn from deck.");
        facade.drawTrainCard();
        facade.finishTurn();
        state.setTurnState(new EndTurnState(state));
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
// pick train card
        System.out.println("inside pick train card");
        System.out.println("Train card picked from face up cards.");
        // check not rainbow card.
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if(pickedCard == null) {
            throw new StateWarning("There is no card in that spot.");
        }
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            throw new StateWarning("Cannot draw a rainbow card if you already have picked a card.");
        } else {
            facade.pickTrainCard(cardIndex);
            facade.finishTurn();
            state.setTurnState(new EndTurnState(state));
        }
    }

    @Override
    public void drawDestinationCard() throws StateWarning {
        throw new StateWarning("You must get another Train card.");
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        throw new StateWarning("You must get another Train card.");
    }

    @Override
    public void claimRoute(int routeId) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void endTurn() throws StateWarning {
        throw new StateWarning("Cannot finish turn yet. You must get another Train card.");
    }

    @Override
    public String toString() {
        return "One Card Drawn";
    }
}
