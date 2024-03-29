package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class OnePickedCardState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public OnePickedCardState(ClientGame playTurnStates)  {

        state = playTurnStates;
        facade = Facade.getInstance();
    }


    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        // draw train card
        System.out.println("another card added from the deck");
        if(ClientModel.getInstance().getNumberOfTrainCards() == 0){
            throw new StateWarning("No remaining train cards");
        }
        facade.drawTrainCard();
        facade.finishTurn();
        state.setTurnState(new EndTurnState(state));;

    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        System.out.println("Another card picked from the face up cards.");
        // pick train card
        // check not rainbow card.
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if(pickedCard == null) {
            throw new StateWarning("There is not card in that spot.");
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
    public void drawDestinationCards() throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void putBackDestinationCards(DestinationCard[] cards) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void claimRoute(int routeId, TrainType type) throws StateWarning {
        throw new StateWarning("Already drew card. You must get another Train card.");
    }

    @Override
    public void endTurn() throws StateWarning {
        throw new StateWarning("Cannot end turn now. You must get another Train card.");
    }

    @Override
    public String toString() {
        return "One Card Picked";
    }
}
