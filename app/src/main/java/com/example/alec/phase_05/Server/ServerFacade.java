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
        //    {"gameName":"Hillary Clinton","hostColor":"green","numberOfPlayers":4,"password":"55","userName":"andrew","commandName":"CreateGame"}     example requestbody. Used for fake client.
        ServerModel model = ServerModel.get_instance();
        Player[] players = new Player[numOfPlayers];
        String[] colors = new String[numOfPlayers];
        players[0] = hostPlayer;
        colors[0] = hostColor;

        GameDescription newGameInfo = new GameDescription(ServerModel.getNextValidGameID(), gameName, numOfPlayers, players, colors);
        if(!model.createGame(newGameInfo))
            return null;
        return newGameInfo;
    }

    public GameDescription joinGame(Player newPlayer, int gameID, String color) {
        ServerModel model = ServerModel.get_instance();
        GameState game = model.getGame(gameID);
        if(game == null) return null;
        if(game.hasPlayer(newPlayer)) return null;
        game.addPlayer(newPlayer);
        return game.getGameDescription();
    }

    public List<GameDescription> getGames(String username, String password) {
        //{"password":"55","userName":"andrew","commandName":"GetGameList"}
        ServerModel model = ServerModel.get_instance();
        return model.getGameDescriptions();
    }

    public GameState getGame(String username, String password, int gameID) {
        return ServerModel.get_instance().getGame(gameID);
    }

    public List<ICommand> getGameUpdates(Player client, int gameID, int lastUpdate) {
        return ServerModel.get_instance().getGameUpdates(client, gameID, lastUpdate);
    }

    public GameDescription getGameDescription(String username, String password, int gameID) {
        return ServerModel.get_instance().getGameDescription(gameID);
    }

    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }
}
