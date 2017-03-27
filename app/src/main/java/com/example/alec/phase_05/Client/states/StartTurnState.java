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
    }

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



    public void removeCardsFromHand(Player player, List<TrainCard> cardsToRemove) {
        List<TrainCard> cardList = player.getTrainCards();
        TrainType trainType = cardList.get(0).getType();
        int size = cardsToRemove.size();
        for(int i = cardList.size(); i > 0; i--) {
            TrainCard tempCard = cardList.get(i);
            if (tempCard.getType().equals(trainType)) {
                cardList.remove(i);
                player.removeTrainCard(i);
                size--;
                // Add card back to the deck.
                if (size == 0) {
                    break;
                }
            }
        }
    }


    public List<TrainCard> findCardsFromHand(Player player, int routeId) {
        boolean isInHand = false;
        List<TrainCard> cardList = player.getTrainCards();
        Route route = state.getRouteByID(routeId);
        TrainType routeType = route.getType();
        int numberOfCardsNeeded = route.getLength();
        List<TrainCard> cardsNeededforRoute = new ArrayList<>();
        for (int i = 0; i < cardList.size(); i++) {
            TrainCard tempCard = cardList.get(i);
            if (tempCard.getType().equals(routeType)) {
                cardsNeededforRoute.add(tempCard);
            }

            if (numberOfCardsNeeded == cardsNeededforRoute.size()) {
                isInHand = true;
                break;
            }

        }

        if(isInHand) {
            return cardsNeededforRoute;
        }
        List<TrainCard> emptyList = new ArrayList<>();
        return emptyList;

    }

    @Override
    public void claimRoute(String player, int routeId) throws StateWarning {
        System.out.println("Claiming route.");
        // claim route
        // get player hand and check that it has the needed cards
        // send in those cards.
        Player currentPlayer = (Player) state.getPlayerByName(player);
        List<TrainCard> cardsFromHand = findCardsFromHand(currentPlayer, routeId);
        if (cardsFromHand.size() == 0) {
            throw new StateWarning("You do not have enough of those cards in your hand to " +
                    "claim that route. Please draw a train card or destination card.");
        } else {
            //removeCardsFromHand(currentPlayer, cardsFromHand);
            state.claimRoute(player, routeId);
            state.setTurnState(new ClaimRouteState(state));
        }



    }

    @Override
    public void endTurn(String player) throws StateWarning {
        throw new StateWarning("You must either draw train cards, " +
                "draw destination cards, or claim a route before you can end your turn.");
    }
}
