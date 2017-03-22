package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class StartTurnState implements GameState {
    ClientGame state = null;

    public StartTurnState(ClientGame playerTurnStates) { state = playerTurnStates; }

    @Override
    public void drawTrainCardFromDeck(Game game, String player) {
        System.out.println("Drawing Train Card from Deck.");
        //draw card from deck
        state.setTurnState(state.getOneDrawnCardState());
    }

    @Override
    public void pickTrainCard(Game game, String player, int cardIndex) {
        System.out.println("Picking Train card from face up cards.");
        // pick card
        // if card is a rainbow -> state.setCardState(state.getRainbowCardState());
        state.setTurnState(state.getOnePickedCardState());

    }

    @Override
    public void drawDestinationCard(Game game, String player) {
        System.out.println("Drawing a destination card.");
        //draw destination card
        state.setTurnState(state.getDrawDestinationState());
    }

    @Override
    public void putBackDestinationCard(Game game, String player, DestinationCard card) {
        throw new StateWarning("You must draw some destination cards first.");
    }

    @Override
    public void claimRoute(Game game, String player, int routeId) {
        System.out.println("Claiming route.");
        // claim route
        state.setTurnState(state.getClaimRouteState());
    }

    @Override
    public void endTurn(Game game, String player) {
        throw new StateWarning("You must either draw train cards, " +
                "draw destination cards, or claim a route before you can end your turn.");
    }
}
