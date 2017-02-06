package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Server.ParseCommand;
import com.example.alec.phase_05.Server.ToLowerCaseCommand;
import com.example.alec.phase_05.Server.TrimCommand;
import com.example.alec.phase_05.Shared.BaseCommand;
import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.IStringProcessor;
import com.example.alec.phase_05.Shared.Player;
import com.example.alec.phase_05.Shared.Results;
import com.example.alec.phase_05.Shared.Serializer;

import java.util.List;

/**
 * Created by Alec on 1/20/17.
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

    public ServerProxy(String host, String port) {
        assert host != null;
        assert host.length() >0;
        assert port != null;
        int test = Integer.parseInt(port);
        assert test > 0;

        myHost = host;
        myPort = port;

    }

    /*

    public ServerProxy(String str, String cmd, ClientCommunicator cc, String serverHost, String serverPort){
        myRtn = new String();
        myCmd = new String(cmd);
        myHost = new String(serverHost);
        myPort = new String(serverPort);
        baseCMD = new BaseCommand(str);
        myStr = new String(str);
        myCC = cc;
       // execute();
    }
/*
    public void execute(){
        myRtn = new String();
        switch(myCmd){
            case "L":
                myRtn = toLowerCase(myStr);
                break;
            case "T":
                myRtn = trim(myStr);
                break;
            case "P":
                myRtn = parseInteger(myStr).toString();
                break;
            default:
                myRtn = "Invalid Input";
                break;
        }
        System.out.print("Result after execution is: \n\n"+myRtn+"\n\n");
    }

*/

    public String des(String json){
        Serializer ser = new Serializer();
        Results myRes;
        myRes = ser.deserializeResults(json);
        return myRes.getData();
    }


    public static IServer getInstance() {
        if(instance == null) {
            instance = new ServerProxy(DEFAULT_HOST, DEFAULT_PORT);
        }
        return instance;
    }

    @Override
    public void configure(String host, String port) {
        myHost = host;
        myPort = port;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean registerUser(String username, String password) {
        return false;
    }

    @Override
    public Game createGame(Player hostPLayer, int numOfPlayers, String gameName) {
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
