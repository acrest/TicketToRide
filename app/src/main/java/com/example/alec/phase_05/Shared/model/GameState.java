package com.example.alec.phase_05.Shared.model;


/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface GameState {
    void drawTrainCardFromDeck() throws StateWarning;

    void pickTrainCard(int cardIndex) throws StateWarning;

    void drawDestinationCards() throws StateWarning;

    void putBackDestinationCards(DestinationCard[] card) throws StateWarning;

    void claimRoute(int routeId, TrainType type) throws StateWarning;

    void endTurn() throws StateWarning;
}
