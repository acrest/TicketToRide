package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.command.CommandHolder;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public interface IServer {
    Player login(String username, String password);
    Player registerUser(String username, String password);
    GameState createGame(Player hostPlayer, int numOfPlayers, String gameName, String hostColor);
    GameState joinGame(Player newPlayer, int gameID, String color);
    GameDescriptionHolder getGames(String username, String password);
    GameDescription getGameDescription(String username, String password, int gameID);


    List<Player> getLatestPlayers(String username, String password, int gameID);
    Game getGame(String username, String password, int gameID);

    CommandHolder getGameCommands(Player player, int gameID);

    GameState getGameState(String username, String password, int gameID);
}
