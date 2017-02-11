package com.example.alec.phase_05.Client.Presenter;

/**
 * Created by samuel on 2/11/17.
 */

public interface IGameStationListener {
    void hideRed(boolean visible);

    void hideGreen(boolean visible);

    void hideBlue(boolean visible);

    void hideYellow(boolean visible);

    void hideBlack(boolean visible);

    void updateGameList();

    void joinGameSuccess(boolean success);

    void createGameSuccess(boolean success);
}
