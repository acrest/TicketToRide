package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;
import java.util.Map;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {
    void drawTrainCard();

    void pickTrainCard(int index);

    void claimRoute(int routID);

    void drawDestinationCard();

    void returnDestinationCard(DestinationCard cardID);

    void endTurn();

    void updateAll();

    void startDemo();

    List<PlayerStat> getPlayerStats();

    void chooseDestinationCards(List<DestinationCard> chosen, List<DestinationCard> notChosen);

    Map<Player, Integer> getLongestPlayer();

    String longestPath();

}
