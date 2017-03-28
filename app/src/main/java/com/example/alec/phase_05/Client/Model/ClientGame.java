package com.example.alec.phase_05.Client.Model;


import com.example.alec.phase_05.Client.states.ClaimRouteState;
import com.example.alec.phase_05.Client.states.DrawDestinationState;
import com.example.alec.phase_05.Client.states.EndTurnState;
import com.example.alec.phase_05.Client.states.OneDrawnCardState;
import com.example.alec.phase_05.Client.states.OneDrawnOnePickedCardState;
import com.example.alec.phase_05.Client.states.OnePickedCardState;
import com.example.alec.phase_05.Client.states.RainbowCardState;
import com.example.alec.phase_05.Client.states.ReturnDestinationState;
import com.example.alec.phase_05.Client.states.StartTurnState;
import com.example.alec.phase_05.Client.states.TwoDrawnCardState;
import com.example.alec.phase_05.Client.states.TwoPickedCardState;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    private GameState turnState = null;
    private String currentPlayerTurn;

    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {

        super(id, name, maxPlayers, bank, gameMap);
        turnState = new StartTurnState(this);
        currentPlayerTurn = null;

    }

    @Override
    public void doDrawTrainCardFromDeck() throws StateWarning {
        turnState.drawTrainCardFromDeck();
    }

    @Override
    public void doPickTrainCard(int cardIndex) throws StateWarning {
        turnState.pickTrainCard(cardIndex);
    }

    @Override
    public void doDrawDestinationCard() throws StateWarning {
        turnState.drawDestinationCard();
    }

    @Override
    public void doPutBackDestinationCard(DestinationCard card) throws StateWarning {
        turnState.putBackDestinationCard(card);
    }

    @Override
    public void doClaimRoute(int routeId) throws StateWarning {
        turnState.claimRoute(routeId);
    }

    @Override
    public void doEndTurn() throws StateWarning {
        turnState.endTurn();
    }

    @Override
    public void setTurnState(GameState state) {
        turnState = state;
    }

    @Override
    public void decNumberOfDestinationCards() {
        ((IClientBank) getBank()).decNumberOfDestinationCards();
    }

    @Override
    public void incNumberOfDestinationCards() {
        ((IClientBank) getBank()).incNumberOfDestinationCards();
    }

    @Override
    public void decNumberOfTrainCards() {
        ((IClientBank) getBank()).decNumberOfTrainCards();
    }

    @Override
    public void setVisibleCard(int index, TrainCard card) {
        ((IClientBank) getBank()).setVisibleCard(index, card);
    }

    @Override
    public GameMap getMap() {
        return getGameMap();
    }

    @Override
    public void setMap(GameMap map) {
        setGameMap(map);
    }

//    @Override
//    public DestinationCard drawDestinationCard(String player) {
//        return null;
//    }
//
//    @Override
//    public boolean putBackDestinationCard(String player, DestinationCard card) {
//        return false;
//    }
//
//    @Override
//    public TrainCard drawTrainCardFromDeck(String player) {
//        return null;
//    }
//
//    @Override
//    public TrainCard pickTrainCard(String player, int cardIndex) {
//        return null;
//    }
//
//    @Override
//    public void claimRoute(String player, int routeId) {
//
//    }

    @Override
    public String getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }


    @Override
    public void setCurrentPlayerTurn(String currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    //The player passed to this function is the player whose turn is ending.
    @Override
    public void endTurn() {
        int index = -1;
        for(int i = 0; i < getNumberPlayers(); i++) {
            IPlayer p = getPlayer(i);
            if(p != null && p.getName().equals(currentPlayerTurn)) {
                index = i;
                break;
            }
        }
        if(index == -1) return;
        for(int i = index + 1; i != index; i = (i + 1) % getNumberPlayers()) {
            IPlayer p = getPlayer(i);
            if(p != null) {
                currentPlayerTurn = p.getName();
                break;
            }
        }
        if(currentPlayerTurn.equals(ClientModel.getInstance().getCurrentPlayerName())) {
            turnState = new StartTurnState(this);
        }
    }
}
