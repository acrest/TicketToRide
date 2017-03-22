package com.example.alec.phase_05.Shared.model;

/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface EachGameState {

    public void drawDestinationCard(Game game, String player);
    public boolean putBackDestinationCard(Game game,String player, DestinationCard card);
    public TrainCard drawTrainCardFromDeck(Game game, String player);
    public TrainCard pickTrainCard(Game game, String player, int cardIndex);
    public void claimRoute(Game game, String player, int routeId);
    public void endTurn(Game game, String player);
}
