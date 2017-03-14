package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {
    void drawTrainCard();

    void pickTrainCard(int index);

    void discardTrainCard(TrainCard card);

    void claimRoute(int routID);

    void drawDestinationCards();

    void returnDestinationCard(Integer cardID);

    void endTurn();

    void updateAll();

    void startDemo();

    List<PlayerStat> getPlayerStats();
}
