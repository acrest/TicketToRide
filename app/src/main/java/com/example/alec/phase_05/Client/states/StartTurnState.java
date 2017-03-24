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

public class StartTurnState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public StartTurnState(ClientGame playerTurnStates) { state = playerTurnStates; }

    @Override
    public void drawTrainCardFromDeck(String player) throws StateWarning {
        System.out.println("Drawing Train Card from Deck.");
        //draw card from deck
        facade.drawTrainCard();
        state.setTurnState(new OneDrawnCardState(state));
    }

    @Override
    public void pickTrainCard(String player, int cardIndex) throws StateWarning {
        System.out.println("Picking Train card from face up cards.");
        // pick card
        // if card is a rainbow -> state.setCardState(state.getRainbowCardState());
        facade.pickTrainCard(cardIndex);
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            state.setTurnState(new RainbowCardState(state));
        } else {
            state.setTurnState(new OnePickedCardState(state));
        }

    }

    @Override
    public void drawDestinationCard(String player) throws StateWarning  {
        System.out.println("Drawing a destination card.");
        //draw destination card
        int drawThree = 3;
        for (int i = 0; i < drawThree; i++) {
            facade.drawDestinationCard();
        }
        state.setTurnState(new DrawDestinationState(state));
    }

    @Override
    public void putBackDestinationCard(String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("You must draw some destination cards first.");
    }

    @Override
    public void claimRoute(String player, int routeId) throws StateWarning {
        System.out.println("Claiming route.");
        // claim route
        facade.claimRoute(routeId);
        state.setTurnState(new ClaimRouteState(state));
    }

    @Override
    public void endTurn(String player) throws StateWarning {
        throw new StateWarning("You must either draw train cards, " +
                "draw destination cards, or claim a route before you can end your turn.");
    }
}
