package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

import java.util.HashMap;
import java.util.Map;

public class ServerModel {

    private static int nextValidGameID = 0;

    private Map<Integer,Game> gamesMap;
    private Map<String,Player> playerMap;

    private static ServerModel _instance;

    public ServerModel() {

    }

    public static ServerModel get_instance(){
        if(_instance == null){
            _instance = new ServerModel();
        }
        return _instance;
    }

    public static int getNextValidGameID() {
        return nextValidGameID++;
    }

    public boolean addPlayer(Player newPlayer){
        String playerName = newPlayer.getName();
        if(playerMap.containsKey(playerName))
            return false;
        playerMap.put(playerName,newPlayer);
        return true;
    }

    public boolean addGame(Game newGame){
        Integer gameID = newGame.ID;
        if(gamesMap.containsKey(gameID))
            return false;
        gamesMap.put(gameID,newGame);
        return true;
    }

    public boolean login(String inputPass, String playerName){
        if(playerMap.get(playerName).getPassword() == inputPass){
            return true;
        }
        else{
            return false;
        }
    }

    public Game getGame(Integer gameID){
        return gamesMap.get(gameID);
    }

    public Player getPlayer(String playerName){
        return playerMap.get(playerName);
    }



}
