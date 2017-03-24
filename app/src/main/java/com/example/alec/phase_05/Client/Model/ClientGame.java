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
    GameState oneDrawnCardState = new OneDrawnCardState(this);
    GameState onePickedCardState = new OnePickedCardState(this);
    GameState rainbowCardState = new RainbowCardState(this);
    GameState oneDrawnOnePickedCardState = new OneDrawnOnePickedCardState(this);
    GameState twoDrawnCardState = new TwoDrawnCardState(this);
    GameState twoPickedCardState = new TwoPickedCardState(this);
    GameState startTurnState = new StartTurnState(this);
    GameState endTurnState = new EndTurnState(this);
    GameState drawDestinationState = new DrawDestinationState(this);
    GameState returnDestinationState = new ReturnDestinationState(this);
    GameState claimRouteState = new ClaimRouteState(this);
    GameState turnState = null;


    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {

        super(id, name, maxPlayers, bank, gameMap);
        turnState = startTurnState;


    }

    public void drawCard(Game game, String player) throws StateWarning {
        turnState.drawDestinationCard(game, player);
    }


    public void pickCard(Game game, String player, int index) throws StateWarning {
        turnState.pickTrainCard(game, player, index);
    }


    public void endTurn(Game game, String player) throws StateWarning {
        turnState.endTurn(game, player);
    }

    public void setTurnState(GameState playerTurnState) { turnState = playerTurnState; }

    public void setState(GameState state) {
        turnState = state;
    }


    @Override
    public void decNumberOfDestinationCards() {

    }

    @Override
    public void incNumberOfDestinationCards() {

    }

    @Override
    public void decNumberOfTrainCards() {

    }

    @Override
    public void setVisibleCard(int index, TrainCard card) {

    }

    @Override
    public GameMap getMap() {
        return null;
    }

    @Override
    public void setMap(GameMap map) {

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

    public GameState getCardState() {
        return turnState;
    }

    public void setOneDrawnCardState(GameState oneDrawnState) {
        oneDrawnCardState = oneDrawnState;
    }

    public GameState getOneDrawnCardState() {
        return oneDrawnCardState;
    }

    public void setNoCardState(GameState noCardState) {
        this.startTurnState = noCardState;
    }

    public GameState getNoCardState() {
        return startTurnState;
    }

    public void setOneDrawnOnePickedCardState(GameState oneDrawnOnePickedState) {
        oneDrawnOnePickedCardState = oneDrawnOnePickedState;
    }

    public GameState getOneDrawnOnePickedCardState() {
        return oneDrawnOnePickedCardState;
    }

    public void setOnePickedCardState(GameState onePickedState) {
        onePickedCardState = onePickedState;
    }

    public GameState getOnePickedCardState() {
        return onePickedCardState;
    }

    public void setRainbowCardState(GameState rainbowState) {
        rainbowCardState = rainbowState;
    }

    public GameState getRainbowCardState() {
        return rainbowCardState;
    }

    public void setTwoDrawnCardState(GameState twoDrawnState) {
        twoDrawnCardState = twoDrawnState;
    }

    public GameState getTwoDrawnCardState() {
        return twoDrawnCardState;
    }

    public void setTwoPickedCardState(GameState twopickedState) {
        twoPickedCardState = twopickedState;
    }

    public GameState getTwoPickedCardState() {
        return twoPickedCardState;
    }

    public void setEndTurnState(GameState endTurnState) {
        this.endTurnState = endTurnState;
    }

    public GameState getEndTurnState() {
        return endTurnState;
    }

    public void setReturnDestinationState(GameState putbackDestinationState) {
        returnDestinationState = putbackDestinationState;
    }

    public GameState getReturnDestinationState() {return returnDestinationState;}

    public void setDrawDestinationState(GameState drawDestState) {
        drawDestinationState = drawDestState;
    }

    public GameState getDrawDestinationState() {return drawDestinationState;}

    public void setClaimRouteState(GameState claimedRoute) { claimRouteState = claimedRoute; }

    public GameState getClaimRouteState() {return claimRouteState;}

}
