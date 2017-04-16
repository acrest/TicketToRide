package com.example.alec.phase_05.Shared.model;

import java.io.Serializable;

/**
 * Created by samuel on 2/22/17.
 */

public interface IGame extends Serializable {
    int NUM_VISIBLE_CARDS = 5;

    int getID();

    String getName();

    IPlayer getPlayer(int position);

    IPlayer getPlayerByName(String playerName);

    void setPlayer(int position, IPlayer player);

    int addPlayerAtNextPosition(IPlayer player);

    int getNumberPlayers();

    int getMaxPlayers();

    TrainCard getVisibleCard(int index);

    int getNumberOfTrainCards();

    int getNumberOfDestinationCards();

    City getCityByName(String name);

    void addCity(City city);

    Route getRouteByID(int routeID);

    void addRoute(Route route);

    boolean isGameStarted();

    void setGameStarted();
}
