package com.example.alec.phase_05.Shared.model;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface EachGameState {

    public DestinationCard drawDestinationCard(String player);
    public boolean putBackDestinationCard(String player, DestinationCard card);
    public TrainCard drawTrainCardFromDeck(String player);
    public TrainCard pickTrainCard(String player, int cardIndex);
    public void claimRoute(String player, int routeId);
    public void endTurn(String player);
}
