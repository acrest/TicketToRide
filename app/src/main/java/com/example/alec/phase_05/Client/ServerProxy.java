package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameListCommand;
import com.example.alec.phase_05.Client.command.ClientJoinGameCommand;
import com.example.alec.phase_05.Client.command.ClientLoginCommand;
import com.example.alec.phase_05.Client.command.ClientRegisterCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.Result;

import java.util.List;

/**
 * Created by clarkpathakis on 2/7/17.
 */

public class ServerProxy implements IServer {
//    String myRtn;
//    String myCmd;
//    String myHost;
//    String myPort;
//    String myStr;
//    BaseCommand baseCMD;
    private ClientCommunicator myCC;
    private static IServer instance = null;
    private static String DEFAULT_HOST = "localhost";
    private static String DEFAULT_PORT = "8080";



    public ServerProxy(String serverHost, String serverPort) {
//        myHost = serverHost;
//        myPort = serverPort;
        myCC = myCC.getInstance();
        if (serverHost == null) {
            myCC.setServerIP(DEFAULT_HOST);
        } else {
            myCC.setServerIP(serverHost);
        }
        if (serverPort == null) {
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



//    public String createCommand() {
//        return null;
//    }


//    @Override
//    public void configure(String host, String port) {
//
//    }

    @Override
    public Player login(String username, String password) {
//        baseCMD = new ClientLoginCommand(username, password);
        ICommand cmd = new ClientLoginCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);
        return (Player) result.toClass(Player.class);
    }

    @Override
    public Player registerUser(String username, String password) {
//        baseCMD = new ClientRegisterCommand(username, password);
        ICommand cmd = new ClientRegisterCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);
        return (Player) result.toClass(Player.class);
    }

    @Override
    public GameDescription createGame(Player hostPlayer, int numOfPlayers, String gameName) {
        //List<GameDescription> gameList = getGames(hostPlayer.getName(), hostPlayer.getPassword());
        //int gameID = gameList.size() + 1;

//        baseCMD = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers);
        ICommand cmd = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers);
        Result result = myCC.executeCommandOnServer(cmd);
        return (GameDescription) result.toClass(GameDescription.class);
    }

    @Override
    public String joinGame(Player newPlayer, int gameID) {
//        baseCMD = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID);
        ICommand cmd = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        return result.toString();
    }

    @Override
    public List<GameDescription> getGames(String username, String password) {
//        baseCMD = new ClientGetGameListCommand(username, password);
        ICommand cmd = new ClientGetGameListCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);
        return (List<GameDescription>) result.toClass(List.class);
    }


    @Override
    public void getLatestPlayers(String username, String password) {

    }

    @Override
    public GameState getGame(String username, String password, int gameID) {

        ICommand cmd = new ClientGetGameCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        return (GameState) result.toClass(GameState.class);
    }
}
