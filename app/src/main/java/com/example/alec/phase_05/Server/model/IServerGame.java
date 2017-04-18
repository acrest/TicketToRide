package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IChatManager;
import com.example.alec.phase_05.Shared.model.IGame;
import com.example.alec.phase_05.Shared.model.PlayerTurnStatus;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by samuel on 2/25/17.
 */

public interface IServerGame extends IGame, Serializable {
    IChatManager getChatManager();

    int getCommandSize();

    void addCommand(GameCommand command);

    GameCommand getCommand(int index);

    ICommand recentCommand(String playerName);

    void clearCommands();

    TrainCard drawTrainCard(String playerName);

    TrainCard pickTrainCard(String playerName, int index);

    DestinationCard drawDestinationCard(String playerName);

    GameMap getMap();

    Map<String, Integer> getBonusPoints();

    void addDestinationCardToBottom(DestinationCard card);

    void removeCardFromPlayerHand(String playerName, DestinationCard card);

    public PlayerTurnStatus getTurnStatus();

    public int getPlayerTurnIndex();

    void transferChoices(String playerName, DestinationCard[] returnedCards);

    DestinationCard[] drawDestinationCards(String playerName);

    Set<String> getHaveDrawnInitialDestinationCards();

    String getLastPlayerTurn();
}
