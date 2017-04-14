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

    GameInfo createGame(String playerName, int numOfPlayers, String gameName, String hostColor);

    GameInfo joinGame(String playerName, int gameID, String color);

    GameInfo reJoinGame(String playerName, int gameID);

    List<GameDescription> getGames();

    GameDescription getGameDescription(int gameID);

    List<IPlayer> getLatestPlayers(int gameID);

    Game getGame(int gameID);

    ICommand getNextCommand(String player, int gameID);

    GameInfo getGameState(int gameID);

    boolean claimRoute(String playerName, int gameId, int routeId, TrainType type);

    TrainCard drawTrainCard(String playerName, int gameId);

    boolean discardTrainCard(String playerName, int gameId, TrainCard card);

    TrainCard pickTrainCard(String playerName, int gameId, int index);

    DestinationCard[] drawDestinationCards(String playerName, int gameId);

    boolean returnDestinationCards(String playerName, int gameId, DestinationCard[] card);

    boolean startGame(int gameId);

    boolean finishTurn(String playerName, int gameId);

    boolean finishGame(String playerName, int gameId);

    boolean sendChat(Chat chat);

    boolean setServerTrainCount(int count);

    Result executeCommand(ICommand command);

    GameInfo getGameInfo(int gameId);
}
