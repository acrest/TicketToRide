package com.example.alec.phase_05.Shared.model;


/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface GameState {
    void drawTrainCardFromDeck() throws StateWarning;

    void pickTrainCard(int cardIndex) throws StateWarning;

    void drawDestinationCard() throws StateWarning;

    void putBackDestinationCard(DestinationCard card) throws StateWarning;

    void claimRoute(int routeId, TrainType type) throws StateWarning;

    void endTurn() throws StateWarning;
}
