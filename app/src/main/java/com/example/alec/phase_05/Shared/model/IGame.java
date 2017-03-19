package com.example.alec.phase_05.Shared.model;

/**
 * Created by samuel on 2/22/17.
 */

public interface IGame {
    int NUM_VISIBLE_CARDS = 5;

    int getID();

    String getName();

    IPlayer getPlayer(int position);

    void setPlayer(int position, IPlayer player);

    int addPlayerAtNextPosition(IPlayer player);

    int getNumberPlayers();

    int getMaxPlayers();

    IBank getBank();

    GameMap getMap();

    void setMap(GameMap map);

    boolean isGameStarted();

    void setGameStarted();

    void setGameState(EachGameState gameState);
}
