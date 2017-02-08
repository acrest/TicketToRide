package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public interface IServer {



    void configure(String host, String port);
    boolean login(String username, String password);
    boolean registerUser(String username, String password);
    Game createGame(Player hostPLayer, int numOfPlayers, String gameName);
    String joinGame(Player newPlayer, int gameID);
    List<Game> getGames();
}
