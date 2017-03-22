package com.example.alec.phase_05.Shared.model;



/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface GameState {


    public void drawTrainCardFromDeck(Game game, String player) throws StateWarning;
    public void pickTrainCard(Game game, String player, int cardIndex) throws StateWarning;

    public void drawDestinationCard(Game game, String player)throws StateWarning;
    public void putBackDestinationCard(Game game,String player, DestinationCard card)throws StateWarning;

    public void claimRoute(Game game, String player, int routeId)throws StateWarning;
    public void endTurn(Game game, String player) throws StateWarning;
}
