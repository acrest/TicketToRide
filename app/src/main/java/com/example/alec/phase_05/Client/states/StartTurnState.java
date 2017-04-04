package com.example.alec.phase_05.Client.states;

import android.widget.ListView;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IGame;
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

    // Checks to see if the player is able to draw / pick another card.
    // Used to determine if they need to end turn early after drawing a card
    // because they are unable to draw another one when they normally would.
    private boolean canDrawOrPick(int savedTrainCardCount, int trainCardChosen) {
        // Just discovered that the first condition can never be true, but leaving it just in case.
        // The first condition is the case where the deck is not empty, but there are no cards to pick.
        // This is impossible, since cards from the deck are used to fill the choices.
        return savedTrainCardCount > 0 || canPick(trainCardChosen);
    }

    // Checks to see if there is at least one non-wild card to pick from.
    // trainCardChosen is the card that the player just chose, or -1 for drawing cards.
    // This index should be ignored, since it is about to disappear once the results come back from the server.
    private boolean canPick(int trainCardChosen) {
        for (int i = 0; i < IGame.NUM_VISIBLE_CARDS; i++) {
            if (i != trainCardChosen) {
                TrainCard card = model.getVisibleTrainCard(i);
                if (card != null && !card.getType().equals(TrainType.LOCOMOTIVE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        System.out.println("Drawing Train Card from Deck.");
        // draw card from deck
        if (model.getNumberOfTrainCards() == 0) {
            throw new StateWarning("No remaining train cards");
        }
        // Save train card count before possibly modifying it with drawTrainCard().
        int remainingTrainCards = model.getNumberOfTrainCards();
        facade.drawTrainCard();
        if (canDrawOrPick(remainingTrainCards - 1, -1)) {
            state.setTurnState(new OneDrawnCardState(state));
        } else {
            // Unable to do anything, so end turn.
            facade.finishTurn();
            state.setTurnState(new EndTurnState(state));
        }
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        System.out.println("Picking Train card from face up cards.");
        // pick card
        // if card is a rainbow -> state.setCardState(state.getRainbowCardState());
        TrainCard pickedCard = state.getVisibleCard(cardIndex);
        if (pickedCard == null) {
            throw new StateWarning("There is not card in that spot.");
        }
        //Save train card count.
        int trainCardCount = model.getNumberOfTrainCards();
        facade.pickTrainCard(cardIndex);
        if (pickedCard.getType().equals(TrainType.LOCOMOTIVE)) {
            facade.finishTurn();
            state.setTurnState(new EndTurnState(state));
        } else {
            if (canDrawOrPick(trainCardCount, cardIndex)) {
                state.setTurnState(new OnePickedCardState(state));
            } else {
                // Can't do anything, so end turn.
                facade.finishTurn();
                state.setTurnState(new EndTurnState(state));
            }
        }
    }

    @Override
    public void drawDestinationCard() throws StateWarning {
        System.out.println("Drawing a destination card.");
        // draw destination card
        int numToDraw = Math.min(model.getNumberOfDestinationCards(), 3);
        if (numToDraw == 0) {
            throw new StateWarning("No remaining cards");
        }
        for (int i = 0; i < numToDraw; i++) {
            facade.drawDestinationCard();
        }
        state.setTurnState(new DrawDestinationState(state));
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        throw new StateWarning("You must draw some destination cards first.");
    }

    @Override
    public void claimRoute(int routeId, TrainType type) throws StateWarning {
        TrainType currentType;
        Route route = ClientModel.getInstance().getMap().getRouteByID(routeId);
        if(type != null){
            currentType = type;
        }
        else{
            currentType = route.getType();
        }


        System.out.println("Claiming route.");
        // claim route
        // get player hand and check that it has the needed cards
        // send in those cards.
        Player currentPlayer = (Player) ClientModel.getInstance().getCurrentPlayer();

        System.out.println("STARTTURNSTATE  " + " " + currentPlayer.countCardsOfType(currentType));
        System.out.println("CONTINUING " + currentType.toString() + " " + route.getId());
        List<TrainCard> arr = currentPlayer.getTrainCards();
        for (TrainCard tc : arr) {
            System.out.println(tc.getType());
        }
        if (!currentPlayer.hasCardsForRoute(currentType, route.getLength())) {
            throw new StateWarning("You do not have enough of those cards in your hand to " +
                    "claim that route. Please draw a train card or destination card.");
        } else if (currentPlayer.getTrainCount() < route.getLength()) {
            throw new StateWarning("You do not have enough trains to claim this route.");
        } else {
//            currentPlayer.removeCardsOfType(currentType, route.getLength());
            System.out.println("ROUTE CLAIMED");

            List<TrainCard> playersHand = currentPlayer.getTrainCards();
            int length = route.getLength();
            for (TrainCard card : playersHand) {

                if (card.getType().equals(route.getType()) || route.getType().equals(TrainType.ANY) || card.getType().equals(TrainType.LOCOMOTIVE)) {
                    facade.discardTrainCard(card);
                    length--;
                    if (length == 0) {
                        break;
                    }
                }


//            List<TrainCard> playersHand = currentPlayer.getTrainCards();
//            int length = route.getLength();
//            for (TrainCard card : playersHand) {
//
//                if (card.getType().equals(currentType) || currentType.equals(TrainType.ANY)) {
//                    facade.discardTrainCard(card);
//                    length--;
//                    if (length == 0) {
//                        break;
//                    }
//                }
//
//
//            }


                List<TrainCard> removedCards = currentPlayer.removeCardsForRoute(route.getType(), route.getLength());
                for (TrainCard tCard : removedCards) {
                    facade.discardTrainCard(tCard);

                }
                model.setTrainCount(model.getTrainCount() - route.getLength());
                model.setPlayerPoints(model.getPlayerPoints() + route.getPoints());
                facade.claimRoute(routeId);
                facade.finishTurn();
                state.setTurnState(new EndTurnState(state));
                model.updateTrainCardDisplay();

                List<TrainCard> rCards = currentPlayer.removeCardsForRoute(route.getType(), route.getLength());
                for (TrainCard tcard : removedCards) {
                    facade.discardTrainCard(tcard);

                }
            }

            List<TrainCard> removedCards = currentPlayer.removeCardsForRoute(currentType, route.getLength());
            for (TrainCard card : removedCards) {
                facade.discardTrainCard(card);
            }
            model.setTrainCount(model.getTrainCount() - route.getLength());
            System.out.println("player points currently "+model.getPlayerPoints());
            System.out.println("this is added " + route.getPoints());
            model.setPlayerPoints(model.getPlayerPoints() + route.getPoints());
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
