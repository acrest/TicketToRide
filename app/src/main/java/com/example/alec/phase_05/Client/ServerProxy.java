package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameListCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameUpdatesCommand;
import com.example.alec.phase_05.Client.command.ClientJoinGameCommand;
import com.example.alec.phase_05.Client.command.ClientLoginCommand;
import com.example.alec.phase_05.Client.command.ClientRegisterCommand;
import com.example.alec.phase_05.Client.command.ClientResult;
import com.example.alec.phase_05.Shared.command.CommandHolder;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.google.gson.reflect.TypeToken;

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
        myCC = ClientCommunicator.getInstance();
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

        Player player = null;

        if(result == null)
        {
            return null;
        }

        if(!result.getRawSerializedResult().equals("null"))
        {
            player = new Player(username, password);
        }

        return player;
    }

    @Override
    public Player registerUser(String username, String password) {
//        baseCMD = new ClientRegisterCommand(username, password);
        ICommand cmd = new ClientRegisterCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);

        Player player = null;

        if(result == null)
        {
            return null;
        }

        if(result.toBoolean())
        {
            player = new Player(username, password);
        }

        return player;
    }

    @Override
    public GameDescription createGame(Player hostPlayer, int numOfPlayers, String gameName, String hostColor) {
        //List<GameDescription> gameList = getGames(hostPlayer.getName(), hostPlayer.getPassword());
        //int gameID = gameList.size() + 1;

//        baseCMD = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers);
        ICommand cmd = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers, hostColor);
        Result result = myCC.executeCommandOnServer(cmd);
        System.out.println(result.getRawSerializedResult());
        return (GameDescription) result.toClass(GameDescription.class);
    }

    @Override
    public String joinGame(Player newPlayer, int gameID, String color) {
//        baseCMD = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID);
        ICommand cmd = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID, color);
        Result result = myCC.executeCommandOnServer(cmd);
        return result.toString();
    }

    @Override
    public GameDescriptionHolder getGames(String username, String password) {
//        baseCMD = new ClientGetGameListCommand(username, password);
        ICommand cmd = new ClientGetGameListCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);
        System.out.println(result.getRawSerializedResult());
        return (GameDescriptionHolder) result.toClass(GameDescriptionHolder.class);
    }


    @Override
    public List<Player> getLatestPlayers(String username, String password, int gameID) {

        ICommand cmd = new ClientGetGameCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        GameState currentGame = (GameState) result.toClass(GameState.class);
        return currentGame.getPlayers();
    }

    @Override
    public GameState getGame(String username, String password, int gameID) {

        ICommand cmd = new ClientGetGameCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        return (GameState) result.toClass(GameState.class);
    }

    @Override
    public CommandHolder getGameCommands(Player player, int gameID, int lastUpdate) {
        ICommand cmd = new ClientGetGameUpdatesCommand(player.getName(), player.getPassword(), gameID, lastUpdate);
        Result result = myCC.executeCommandOnServer(cmd);
        return (CommandHolder) result.toClass(CommandHolder.class); //TODO: fix
    }
}
