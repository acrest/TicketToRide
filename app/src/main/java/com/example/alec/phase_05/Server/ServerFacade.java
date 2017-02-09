package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Client.ClientModel;
import com.example.alec.phase_05.Shared.Game;
import com.example.alec.phase_05.Shared.Player;

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

    public boolean login(String username, String password) {
        return ServerModel.get_instance().login(password, username);
    }

    public boolean registerUser(String username, String password) {
        ServerModel model = ServerModel.get_instance();
        Player player = new Player(username, password);
        return model.addPlayer(player);
    }

    public Game createGame(Player hostPLayer, int numOfPlayers, String gameName) {
        ServerModel model = ServerModel.get_instance();
        Game newGame = new Game(ServerModel.getNextValidGameID(), gameName, numOfPlayers);
        if(!model.addGame(newGame))
            return null;
        return newGame;
    }

    public String joinGame(Player newPlayer, int gameID) {
        ServerModel model = ServerModel.get_instance();
        Game game = model.getGame(gameID);
        if(game == null) return null;
        if(game.hasPlayer(newPlayer.getName())) return null;
        game.addPlayer(newPlayer);
        return game.getName();
    }

    public List<Game> getGames(String username, String password) {
        return null;
    }
}
