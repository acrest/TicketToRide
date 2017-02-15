package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerModel {
    private static int nextValidGameID = 0;

    private Map<Integer,GameState> gamesMap;
    private Map<String,Player> playerMap;

    private static ServerModel _instance;

    public ServerModel() {
        playerMap = new HashMap<>();
        gamesMap = new HashMap<>();
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

    public boolean createGame(GameDescription gameInfo){
        int gameID = gameInfo.getID();
        if(gamesMap.containsKey(gameID))
            return true;
        gamesMap.put(gameID,new GameState(gameInfo));
        return false;
    }

    public GameState getGame(Integer gameID){
        return gamesMap.get(gameID);
    }

    public Player getPlayer(String playerName){
        return playerMap.get(playerName);
    }

    public Player login(String inputPass, String playerName){
        Player player = playerMap.get(playerName);
        if(player == null) return null;
        if(player.getPassword().equals(inputPass)){
            return player;
        }
        else{
            return null;
        }
    }

    public List<GameDescription> getGameDescriptions() {
        List<GameDescription> gameDescriptions = new ArrayList<>();
        for (GameState gameState : gamesMap.values()) {
            gameDescriptions.add(gameState.getGameDescription());
        }
        return gameDescriptions;
    }

    public GameDescription getGameDescription(int gameID) {
        if(!gamesMap.containsKey(gameID)) {
            return null;
        }
        return gamesMap.get(gameID).getGameDescription();
    }

    public List<ICommand> getGameUpdates(Player player, int gameID, int lastUpdate) {
        //TODO: implement this
        return null;
    }
}
