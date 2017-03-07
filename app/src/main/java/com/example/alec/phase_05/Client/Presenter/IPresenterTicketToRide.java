package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Shared.model.Player;

import java.util.Observer;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {

    void drawTrainCard(int index);
    void claimRoute(Player player, int routID);
    void drawDestinationCards();
    void endTurn();
}
