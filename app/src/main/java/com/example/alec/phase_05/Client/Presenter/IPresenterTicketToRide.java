package com.example.alec.phase_05.Client.Presenter;

import java.util.Observer;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {

    void drawTrainCard(int index);
    void claimRoute();
    void drawDestinationCards();
    void endTurn();
}
