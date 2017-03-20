package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public interface IClientGame extends IGame {
    IPlayer getPlayerByName(String playerName);

    void decNumberOfDestinationCards();

    void incNumberOfDestinationCards();

    void decNumberOfTrainCards();

    void setVisibleCard(int index, TrainCard card);

    GameMap getMap();

    void setMap(GameMap map);
}
