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
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    private GameState turnState = null;

    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {

        super(id, name, maxPlayers, bank, gameMap);
        turnState = new StartTurnState(this);

    }

    @Override
    public void doDrawTrainCardFromDeck(String player) throws StateWarning {
        turnState.drawTrainCardFromDeck(player);
    }

    @Override
    public void doPickTrainCard(String player, int cardIndex) throws StateWarning {
        turnState.pickTrainCard(player, cardIndex);
    }

    @Override
    public void doDrawDestinationCard(String player) throws StateWarning {
        turnState.drawDestinationCard(player);
    }

    @Override
    public void doPutBackDestinationCard(String player, DestinationCard card) throws StateWarning {
        turnState.putBackDestinationCard(player, card);
    }

    @Override
    public void doClaimRoute(String player, int routeId) throws StateWarning {
        turnState.claimRoute(player, routeId);
    }

    @Override
    public void doEndTurn(String player) throws StateWarning {
        turnState.endTurn(player);
    }

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

    @Override
    public DestinationCard drawDestinationCard(String player) {
        return null;
    }

    @Override
    public boolean putBackDestinationCard(String player, DestinationCard card) {
        return false;
    }

    @Override
    public TrainCard drawTrainCardFromDeck(String player) {
        return null;
    }

    @Override
    public TrainCard pickTrainCard(String player, int cardIndex) {
        return null;
    }

    @Override
    public void claimRoute(String player, int routeId) {

    }

    @Override
    public void endTurn(String player) {

    }
}
