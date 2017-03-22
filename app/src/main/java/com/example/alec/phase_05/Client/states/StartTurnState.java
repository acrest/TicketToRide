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
    public void drawTrainCardFromDeck(Game game, String player) {
        System.out.println("Drawing Train Card from Deck.");
        //draw card from deck
        facade.drawTrainCard();
        state.setTurnState(state.getOneDrawnCardState());
    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) {
        System.out.println("Picking Train card from face up cards.");
        // pick card
        // if card is a rainbow -> state.setCardState(state.getRainbowCardState());
        facade.pickTrainCard(cardIndex);
        TrainCard pickedCard = game.getVisibleCard(cardIndex);
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            state.setTurnState(state.getRainbowCardState());
        } else {
            state.setTurnState(state.getOneDrawnOnePickedCardState());
        }

    }

    @Override
    public void drawDestinationCard(Game game, String player) {
        System.out.println("Drawing a destination card.");
        //draw destination card
        facade.drawDestinationCard();
        state.setTurnState(state.getDrawDestinationState());
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) throws StateWarning {
        throw new StateWarning("You must draw some destination cards first.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) {
        System.out.println("Claiming route.");
        // claim route
        facade.claimRoute(routeId);
        state.setTurnState(state.getClaimRouteState());
    }

    @Override
    public void endTurn(Game game, String player) throws StateWarning {
        throw new StateWarning("You must either draw train cards, " +
                "draw destination cards, or claim a route before you can end your turn.");
    }
}
