package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public interface IClientGame extends IGame {
    void decNumberOfDestinationCards();

    void incNumberOfDestinationCards();

    void decNumberOfTrainCards();

    void setVisibleCard(int index, TrainCard card);

    GameMap getMap();

    void setMap(GameMap map);

    String getCurrentPlayerTurn();

    void setCurrentPlayerTurn(String currentPlayerTurn);

//    DestinationCard drawDestinationCard(String player);
//
//    boolean putBackDestinationCard(String player, DestinationCard card);
//
//    TrainCard drawTrainCardFromDeck(String player);
//
//    TrainCard pickTrainCard(String player, int cardIndex);
//
//    void claimRoute(int routeId);

    void endTurn();

    void doDrawTrainCardFromDeck() throws StateWarning;

    void doPickTrainCard(int cardIndex) throws StateWarning;

    void doDrawDestinationCard() throws StateWarning;

    void doPutBackDestinationCard(DestinationCard card) throws StateWarning;

    void doClaimRoute(int routeId) throws StateWarning;

    void doEndTurn() throws StateWarning;

    void setTurnState(GameState state);
}
