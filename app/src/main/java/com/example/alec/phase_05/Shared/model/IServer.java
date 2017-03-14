package com.example.alec.phase_05.Shared.model;

import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public interface IServer {
    boolean login(String username, String password);
    boolean registerUser(String username, String password);
    GameState createGame(String playerName, int numOfPlayers, String gameName, String hostColor);
    GameState joinGame(String playerName, int gameID, String color);
    List<GameDescription> getGames();
    GameDescription getGameDescription(int gameID);
    List<IPlayer> getLatestPlayers(int gameID);
    Game getGame(int gameID);
    ICommand getNextCommand(String player, int gameID);
    GameState getGameState(int gameID);
    boolean claimRoute(String playerName, int gameId, int routeId);
    TrainCard drawTrainCard(String playerName, int gameId);
    boolean discardTrainCard(String playerName, int gameId, TrainCard card);
    TrainCard pickTrainCard(String playerName, int gameId, int index);
    DestinationCard drawDestinationCard(String playerName, int gameId);
    boolean putBackDestinationCard(String playerName, int gameId, DestinationCard card);
    boolean startGame(int gameId);

    Result executeCommand(ICommand command);
}
