package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.UI.GameStationActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Andrew on 2/9/2017.
 */

public class PresenterGameStation implements IPresenterGameStation {

    private IGameStationListener listener;

    public PresenterGameStation(IGameStationListener listener) {
        this.listener = listener;
    }

    public void joinGame(String color) {
        Facade f = Facade.getInstance();
        //f.joinGame();
    }

    public void createGame(String color, String gameName, int numberOfPlayers) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
