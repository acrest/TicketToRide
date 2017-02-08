package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
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


    public String createCommand() {
        return null;
    }


    @Override
    public void configure(String host, String port) {

    }

    @Override
    public boolean login(String username, String password) {
        baseCMD = new AbstractLoginCommand(username, password) {
            @Override
            public Result execute() {
                return null;
            }
        };
        myCC.executeCommandOnServer(baseCMD);
        return true;
    }

    @Override
    public boolean registerUser(String username, String password) {
        baseCMD = new AbstractRegisterCommand(username, password) {
            @Override
            public Result execute() {
                return null;
            }
        };
        myCC.executeCommandOnServer(baseCMD);
        return true;
    }

    @Override
    public Game createGame(Player hostPLayer, int numOfPlayers, String gameName) {
        /*

        TALK TO SAM ABOUT COMMAND NAME

        baseCMD = new GameCommand("createGame", hostPLayer.name, hostPLayer.password, int gameID ) {
            @Override
            public Result execute() {
                return null;
            }
        };

        */
        return null;
    }

    @Override
    public String joinGame(Player newPlayer, int gameID) {
        return null;
    }

    @Override
    public List<Game> getGames() {
        return null;
    }
}
