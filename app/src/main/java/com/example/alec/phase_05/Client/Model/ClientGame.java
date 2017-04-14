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
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    private GameState turnState = null;
    private String currentPlayerTurn;
    private String lastPlayerTurn;
    private boolean oneMoreTurn;
    private boolean gameFinished;

    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {

        super(id, name, maxPlayers, bank, gameMap);
        turnState = new EndTurnState(this);
        currentPlayerTurn = null;
        gameFinished = false;
        oneMoreTurn = false;
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
    public void doDrawDestinationCards() throws StateWarning {
        turnState.drawDestinationCards();
    }

    @Override
    public void doPutBackDestinationCards(DestinationCard[] cards) throws StateWarning {
        turnState.putBackDestinationCards(cards);
    }


    @Override
    public void doClaimRoute(int routeId, TrainType type) throws StateWarning {
        turnState.claimRoute(routeId, type);
    }

    @Override
    public void doEndTurn() throws StateWarning {
        turnState.endTurn();
    }

    @Override
    public void setTurnState(GameState state) {
        turnState = state;
        ClientModel.getInstance().notifyGameStateChange();
    }

    @Override
    public GameState getTurnState() {
        return turnState;
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
    public void setNumberOfTrainCards(int num) {
        ((IClientBank) getBank()).setNumberOfTrainCards(num);
    }

    @Override
    public void setNumberOfDestinationCards(int num) {
        ((IClientBank) getBank()).setNumberOfDestinationCards(num);
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
        if(oneMoreTurn){
            gameFinished = true;
        }
        if(currentPlayerTurn.equals(lastPlayerTurn)) {
            oneMoreTurn = true;
        }
        if(currentPlayerTurn.equals(ClientModel.getInstance().getCurrentPlayerName())) {
            setTurnState(new StartTurnState(this));
        }
        ClientModel.getInstance().notifyGameStateChange();
    }

    //The player passed to this function is the player whose turn is ending.
    @Override
    public void endTurn(String playerName) {
        if(!playerName.equals(currentPlayerTurn)) throw new IllegalStateException("player \"" + playerName + "\" tried to end turn when it was \"" + currentPlayerTurn + "'s\" turn");
        if (currentPlayerTurn.equals(ClientModel.getInstance().getCurrentPlayerName()) && lastPlayerTurn == null) {
            if(getPlayerByName(currentPlayerTurn).getTrainCount() <= 2) {
                lastPlayerTurn = currentPlayerTurn;
                ClientModel.getInstance().setLastTurns(true);
            }
        }
        int index = -1;
        for (int i = 0; i < getMaxPlayers(); i++) {
            IPlayer p = getPlayer(i);
            if (p != null && p.getName().equals(currentPlayerTurn)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalStateException("Index is -1 in end game");
        }
        for (int i = (index + 1) % getMaxPlayers(); i != index; i = (i + 1) % getMaxPlayers()) {
            IPlayer p = getPlayer(i);
            if (p != null) {
                setCurrentPlayerTurn(p.getName());
                return;
            }
        }
        throw new IllegalStateException("end of end game");
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }
}
