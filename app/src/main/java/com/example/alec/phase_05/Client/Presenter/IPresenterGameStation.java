package com.example.alec.phase_05.Client.Presenter;

import java.util.Observer;

/**
 * Created by samuel on 2/11/17.
 */

public interface IPresenterGameStation extends Observer {

    void joinGame(String color);

    void createGame(String color, String gameName, int numberOfPlayers, String hostColor);

}
