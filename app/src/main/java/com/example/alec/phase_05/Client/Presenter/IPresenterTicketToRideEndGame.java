package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.Iterator;

/**
 * Created by samuel on 3/22/17.
 */

public interface IPresenterTicketToRideEndGame extends IPresenter {
    Iterator<IPlayer> getPlayers();
    String getWinner();
    String getLongestRouteHolder();
}
