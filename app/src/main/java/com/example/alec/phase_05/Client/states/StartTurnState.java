package com.example.alec.phase_05.Client.states;

import android.widget.ListView;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class StartTurnState implements GameState {
    private ClientGame state = null;
    private Facade facade;
    private ClientModel model;

    public StartTurnState(ClientGame playerTurnStates) {
        model = ClientModel.getInstance();
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        System.out.println("Drawing Train Card from Deck.");
        //draw card from deck

        facade.drawTrainCard();
        state.setTurnState(new OneDrawnCardState(state));
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        System.out.println("Picking Train card from face up cards.");
        // pick card
        // if card is a rainbow -> state.setCardState(state.getRainbowCardState());
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if(pickedCard == null) return; //no card in that spot
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            facade.finishTurn();
            state.setTurnState(new EndTurnState(state));
        } else {
            state.setTurnState(new OnePickedCardState(state));
        }
        facade.pickTrainCard(cardIndex);
    }

    @Override
    public void drawDestinationCard() throws StateWarning  {
        System.out.println("Drawing a destination card.");
        //draw destination card
        int drawThree = 3;
        for (int i = 0; i < drawThree; i++) {
            facade.drawDestinationCard();
        }
        state.setTurnState(new DrawDestinationState(state));
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        throw new StateWarning("You must draw some destination cards first.");
    }

    @Override
    public void claimRoute(int routeId) throws StateWarning {
        System.out.println("Claiming route.");
        // claim route
        // get player hand and check that it has the needed cards
        // send in those cards.
        Player currentPlayer = (Player) ClientModel.getInstance().getCurrentPlayer();
        Route route = ClientModel.getInstance().getMap().getRouteByID(routeId);
        System.out.println("STARTTURNSTATE  " + " " + currentPlayer.countCardsOfType(route.getType()));
        System.out.println("CONTINUING " + route.getType() + " " + route.getId());
        List<TrainCard> arr = currentPlayer.getTrainCards();
        for (TrainCard tc : arr) {
            System.out.println(tc.getType());
        }
        if (currentPlayer.countCardsOfType(route.getType()) < route.getLength()) {
            throw new StateWarning("You do not have enough of those cards in your hand to " +
                    "claim that route. Please draw a train card or destination card.");
        } else {
            currentPlayer.removeCardsOfType(route.getType(), route.getLength());
            System.out.println("ROUTE CLAIMED");
            List<TrainCard> playersHand = currentPlayer.getTrainCards();
            int length = route.getLength();
            for (TrainCard card : playersHand) {

                if (card.getType().equals(route.getType()) || route.getType().equals(TrainType.ANY)) {
                    facade.discardTrainCard(card);
                    length--;
                    if (length == 0) {
                        break;
                    }
                }


            }
            facade.claimRoute(routeId);
            facade.finishTurn();
            state.setTurnState(new EndTurnState(state));
            model.updateTrainCardDisplay();
        }
    }

    @Override
    public void endTurn() throws StateWarning {
        throw new StateWarning("You must either draw train cards, " +
                "draw destination cards, or claim a route before you can end your turn.");
    }

    @Override
    public String toString() {
        return "Your Turn";
    }
}
