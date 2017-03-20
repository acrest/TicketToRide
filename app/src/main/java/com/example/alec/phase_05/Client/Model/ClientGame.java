package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.EachGameState;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public class ClientGame extends Game implements IClientGame {
    private EachGameState gameState;

    public ClientGame(int id, String name, int maxPlayers, IClientBank bank, GameMap gameMap) {
        super(id, name, maxPlayers, bank, gameMap);
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
        return gameState.drawDestinationCard(player);
    }

    @Override
    public boolean putBackDestinationCard(String player, DestinationCard card) {
        return gameState.putBackDestinationCard(player, card);
    }

    @Override
    public TrainCard drawTrainCardFromDeck(String player) {
        return null;
    }

    @Override
    public TrainCard pickTrainCard(String player, int cardIndex) {
        return gameState.pickTrainCard(player, cardIndex);
    }

    @Override
    public void claimRoute(String player, int routeId) {
        gameState.claimRoute(player, routeId);
    }

    @Override
    public void endTurn(String player) {
        gameState.endTurn(player);
    }

    public void setState(EachGameState state) {
        gameState = state;
    }
}
