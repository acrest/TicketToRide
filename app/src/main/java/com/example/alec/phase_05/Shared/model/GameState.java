package com.example.alec.phase_05.Shared.model;



/**
 * Created by clarkpathakis on 3/16/17.
 */

public interface GameState {


    public void drawTrainCardFromDeck(String player) throws StateWarning;
    public void pickTrainCard(String player, int cardIndex) throws StateWarning;

    public void drawDestinationCard(String player)throws StateWarning;
    public void putBackDestinationCard(String player, DestinationCard card)throws StateWarning;

    public void claimRoute(String player, int routeId)throws StateWarning;
    public void endTurn(String player) throws StateWarning;
}
