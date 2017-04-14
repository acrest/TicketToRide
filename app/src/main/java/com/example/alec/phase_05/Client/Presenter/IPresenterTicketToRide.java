package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.PlayerStat;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

import java.util.List;
import java.util.Map;

/**
 * Created by Molly on 2/23/2017.
 */

public interface IPresenterTicketToRide extends IPresenter {
    void drawTrainCard();

    void pickTrainCard(int index);

    void claimRoute(int routeID, TrainType type);

    void drawDestinationCards();

    void returnDestinationCards(DestinationCard[] cards);

    void endTurn();

    void updateAll();

    void startDemo();

    List<PlayerStat> getPlayerStats();

    void chooseDestinationCards(List<DestinationCard> chosen, List<DestinationCard> notChosen);

    void sendChat(Chat chat);

    Map<String, Integer> getLongestPlayer();

    String longestPath();
}
