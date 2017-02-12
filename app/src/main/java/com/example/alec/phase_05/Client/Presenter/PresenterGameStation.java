package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.ClientModel;
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
        ClientModel.getInstance().addObserver(this);
    }

    @Override
    public void joinGame(int gameID, String color) {
        Facade.getInstance().joinGame(gameID, color);
    }

    @Override
    public void createGame(String hostColor, String gameName, int numberOfPlayers) {
        Facade.getInstance().createGame(numberOfPlayers, gameName, hostColor);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
