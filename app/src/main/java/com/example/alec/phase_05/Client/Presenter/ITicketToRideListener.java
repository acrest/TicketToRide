package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by Alec on 2/24/17.
 */

public interface ITicketToRideListener {

    void updateTrainCards(List<TrainCard> cards); //main player
    void updateDestinationCards(List<DestinationCard> cards); //main player

    void updatePlayerTrainCards(String playerName, int count); //other player's (don't know their cards)
    void updatePlayerDestinationCards(String playerName, int count); //other player's (don't know their cards)

    void updateChats(List<Chat> chats);
    void updatePlayerPoints(String playerName, int points);
    void updatePlayerTrainCount(String playerName, int count);

    void updateFaceupTrainCards(List<TrainCard> cards); // faceup train cards in the bank;

    void updateMap(GameMap map);
}
