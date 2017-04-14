package com.example.alec.phase_05.Client.Presenter;

import java.util.Observer;

/**
 * Created by samuel on 2/11/17.
 */

public interface IPresenterGameStation extends IPresenter {

    void joinGame(int gameID, String color);

    void reJoinGame(int gameID);

    void createGame(String hostColor, String gameName, int numberOfPlayers);

}
