package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

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
        return model.addPlayer(player);
    }

    public GameDescription createGame(Player hostPLayer, int numOfPlayers, String gameName) {
        ServerModel model = ServerModel.get_instance();
        GameDescription newGame = new GameDescription(ServerModel.getNextValidGameID(), gameName, numOfPlayers);
        if(!model.addGame(newGame))
            return null;
        return newGame;
    }

    public String joinGame(Player newPlayer, String color, int gameID) {
        ServerModel model = ServerModel.get_instance();
        GameState game = model.getGame(gameID);
        if(game == null) return null;
        if(game.hasPlayer(newPlayer.getName())) return null;
        //game.addPlayer(newPlayer);
        return game.getGameDescription().getName();
    }

    public List<GameDescription> getGames(String username, String password) {
        ServerModel model = ServerModel.get_instance();
        return model.getGameDescriptions();
    }

    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }
}
