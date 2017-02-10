package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public interface IServer {
    Player login(String username, String password);
    Player registerUser(String username, String password);
    GameDescription createGame(Player hostPlayer, int numOfPlayers, String gameName);
    String joinGame(Player newPlayer, int gameID);
    List<GameDescription> getGames(String username, String password);
}
