package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerFacade {
    private static ServerFacade _instance;

    public ServerFacade() {

    }

    public static ServerFacade get_instance(){
        if(_instance == null){
            _instance = new ServerFacade();
        }
        return _instance;
    }

    public Player getPlayerByName(String username)
    {
        return ServerModel.get_instance().getPlayer(username);
    }

    public boolean poller() {
        return false;
    }

    public Player login(String username, String password) {
        return ServerModel.get_instance().login(password, username);
    }

    public boolean registerUser(String username, String password) {

        ServerModel model = ServerModel.get_instance();
        Player player = new Player(username, password);
        System.out.println("TEST RESGISTER USER "+player.getName()+" "+player.getPassword());
        return model.addPlayer(player);
    }

    public GameDescription createGame(Player hostPlayer, int numOfPlayers, String gameName, String hostColor) {
        ServerModel model = ServerModel.get_instance();
        List<Player> playerList = new ArrayList<>();
        Map<Player,String> playerMap = new HashMap<>();
        playerList.add(hostPlayer);
        playerMap.put(hostPlayer,hostColor);

        //GameDescription newGame = new GameDescription(ServerModel.getNextValidGameID(), gameName, numOfPlayers, playerList, playerMap);
//        if(!model.addGame(newGame))
//            return null;
//        return newGame;
        return null;
    }

    public String joinGame(Player newPlayer, int gameID, String color) {
        ServerModel model = ServerModel.get_instance();
        GameState game = model.getGame(gameID);
        if(game == null) return null;
        if(game.hasPlayer(newPlayer)) return null;
        game.addPlayer(newPlayer);
        return game.getName();
    }

    public List<GameDescription> getGames(String username, String password) {
        ServerModel model = ServerModel.get_instance();
        return model.getGameDescriptions();
    }

    public List<ICommand> getGameUpdates(Player client, int gameID, int lastUpdate) {
        return ServerModel.get_instance().getGameUpdates(client, gameID, lastUpdate);
    }

    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }
}
