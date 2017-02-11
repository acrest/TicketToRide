package com.example.alec.phase_05.Client.Presenter;

import android.util.Log;

import java.util.Observable;

/**
 * Created by samuel on 2/11/17.
 */

public class MockPresenterGameStation implements IPresenterGameStation {

    private IGameStationListener listener;

    public MockPresenterGameStation(IGameStationListener listener) {
        this.listener = listener;
    }

    @Override
    public void joinGame(String color) {
        Log.d("MockPresenter", "called joinGame with color = " + color);
        listener.joinGameSuccess(true);
    }

    @Override
    public void createGame(String color, String gameName, int numberOfPlayers) {
        Log.d("MockPresenter", "called createGame with color = " + color
                + ", gameName = " + gameName
                + ", numberOfPlayers = " + numberOfPlayers);
        listener.createGameSuccess(true);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
