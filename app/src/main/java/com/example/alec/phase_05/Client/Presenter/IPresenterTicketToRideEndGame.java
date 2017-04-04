package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.Iterator;

/**
 * Created by samuel on 3/22/17.
 */

public interface IPresenterTicketToRideEndGame extends IPresenter {
    Iterator<String> getPlayerNames();

    int getPoints(String playerName);

    int getAdditions(String playerName);

    int getTotal(String playerName);

    String getColor(String playerName);

    String getWinner();

    String getLongestRouteHolder();

    // Return the user to the game list.
    void onReturnButtonPressed();
}
