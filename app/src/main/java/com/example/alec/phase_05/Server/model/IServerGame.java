package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 2/25/17.
 */

public interface IServerGame extends IGame {
    IChatManager getChatManager();

    void addCommand(GameCommand command);

    ICommand recentCommand(String playerName);

    TrainCard drawTrainCard(String playerName);

    TrainCard pickTrainCard(String playerName, int index);

    DestinationCard drawDestinationCard(String playerName);

    GameMap getMap();

    void addDestinationCardToBottom(DestinationCard card);
}
