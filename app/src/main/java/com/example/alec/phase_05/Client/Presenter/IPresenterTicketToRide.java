package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {

    void drawTrainCard();
    void pickTrainCard(int index);
    void claimRoute(int routID);
    void drawDestinationCards();
    void returnDestinationCard(Integer cardID);
    void endTurn();

    void startDemo();

}
