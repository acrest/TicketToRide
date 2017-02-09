package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameListCommand;
import com.example.alec.phase_05.Client.command.ClientJoinGameCommand;
import com.example.alec.phase_05.Client.command.ClientLoginCommand;
import com.example.alec.phase_05.Client.command.ClientRegisterCommand;
import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;
import com.example.alec.phase_05.Shared.command.AbstractLoginCommand;
import com.example.alec.phase_05.Shared.command.AbstractRegisterCommand;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by clarkpathakis on 2/7/17.
 */

public class ServerProxy implements IServer {
    String myRtn;
    String myCmd;
    String myHost;
    String myPort;
    String myStr;
    BaseCommand baseCMD;
    ClientCommunicator myCC;
    private static IServer instance = null;
    private static String DEFAULT_HOST = "localhost";
    private static String DEFAULT_PORT = "8080";



    public ServerProxy(String serverHost, String serverPort) {
        myHost = serverHost;
        myPort = serverPort;
        myCC = myCC.getInstance();
        if (serverHost.equals(null)) {
            myCC.setServerIP(DEFAULT_HOST);
        } else {
            myCC.setServerIP(serverHost);
        }
        if (serverPort.equals(null)) {
            myCC.setServerPort(DEFAULT_PORT);
        } else {
            myCC.setServerPort(serverPort);
        }
    }

    public static IServer getInstance() {
        if(instance == null) {
            instance = new ServerProxy(DEFAULT_HOST, DEFAULT_PORT);
        }
        return instance;
    }



    public String createCommand() {
        return null;
    }


    @Override
    public void configure(String host, String port) {

    }

    @Override
    public boolean login(String username, String password) {
        baseCMD = new ClientLoginCommand(username, password);
        Result result = myCC.executeCommandOnServer(baseCMD);
        return result.toBoolean();
    }

    @Override
    public boolean registerUser(String username, String password) {
        baseCMD = new ClientRegisterCommand(username, password);
        Result result = myCC.executeCommandOnServer(baseCMD);
        return result.toBoolean();
    }

    @Override
    public Game createGame(Player hostPlayer, int numOfPlayers, String gameName) {
        //List<Game> gameList = getGames(hostPlayer.getName(), hostPlayer.getPassword());
        //int gameID = gameList.size() + 1;
        baseCMD = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers);
        Result result = myCC.executeCommandOnServer(baseCMD);
        return (Game) result.toClass(Game.class);
    }

    @Override
    public String joinGame(Player newPlayer, int gameID) {
        baseCMD = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID);
        Result result = myCC.executeCommandOnServer(baseCMD);
        return result.toString();
    }

    @Override
    public List<Game> getGames(String username, String password) {
        baseCMD = new ClientGetGameListCommand(username, password);
        Result result = myCC.executeCommandOnServer(baseCMD);
        return (List<Game>) result.toClass(List.class);
    }
}
