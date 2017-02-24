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

/**
 * Handles the communication between the ServerModel and the CommandHandler
 */
public class ServerFacade {
    private static ServerFacade _instance;

    /**
     * Creates a new ServerFacade
     */
    public ServerFacade() {

    }

    /**
     * Gets the singleton instance of the ServerFacade and creates one if it has not been created
     * @return current instance of ServerFacade
     */
    public static ServerFacade get_instance(){
        if(_instance == null){
            _instance = new ServerFacade();
        }
        return _instance;
    }

    /**
     * Finds the specific player inside the ServerModel
     * @pre username is valid
     * @post
     * @param username the name of the player to find
     * @return the player if he or she exists
     */
    public Player getPlayerByName(String username)
    {
        return ServerModel.get_instance().getPlayer(username);
    }

    /**
     * returns false
     * @return false
     */
    public boolean poller() {
        return false;
    }

    /**
     * calls the login method inside ServerModel
     * @pre username is valid and password is valid
     * @post
     * @param username the username given by the user
     * @param password the password given by the user
     * @return returns the player if the password and username are correct, returns null if they are not correct
     */
    public Player login(String username, String password) {
        return ServerModel.get_instance().login(password, username);
    }

    /**
     * creates a new player using the given username and password and adds it to the ServerModel
     * @pre username is valid and password is valid
     * @post
     * @param username the username given by the user
     * @param password the password given by the user
     * @return returns false if the player already exists, true otherwise
     */
    public boolean registerUser(String username, String password) {

        ServerModel model = ServerModel.get_instance();
        Player player = new Player(username, password);
        System.out.println("TEST RESGISTER USER "+player.getName()+" "+player.getPassword());
        return model.addPlayer(player);
    }

    /**
     * creates a new game and adds it to the ServerModel
     * @pre
     * @post
     * @param hostPlayer the player who created the game
     * @param numOfPlayers the max number of players allowed in the game
     * @param gameName the name of the game
     * @param hostColor the color of the hostPlayer
     * @return true if the game is successfully added to the ServerModel, false otherwise
     */
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



    /**
     * adds a player to an existing game inside the ServerModel
     * @pre
     * @post
     * @param newPlayer the player to be added to the game
     * @param gameID the ID of the game to be added to
     * @param color the chosen color of the newPlayer
     * @return false if the game does not exist or the player is already in the game, true otherwise
     */
    public GameDescription joinGame(Player newPlayer, int gameID, String color) {
        ServerModel model = ServerModel.get_instance();
        GameState game = model.getGame(gameID);
        if(game == null) return null;
        if(game.hasPlayer(newPlayer)) return null;
        game.addPlayer(newPlayer);
        game.setPlayerColor(newPlayer, color);
        return game.getGameDescription();
    }

    /**
     * gets the list of games from the ServerModel that are available to join
     * @pre
     * @post
     * @param username the username of the player
     * @param password the password of the player
     * @return a list of available games to join
     */
    public List<GameDescription> getGames(String username, String password) {
        //{"password":"55","userName":"andrew","commandName":"GetGameList"}
        ServerModel model = ServerModel.get_instance();
        return model.getGameDescriptions();
    }

    /**
     * gets a specific GameState based on the given gameID
     * @pre
     * @post
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the GameSTate of the given gameID, returns null if the game does not exist
     */
    public GameState getGame(String username, String password, int gameID) {
        return ServerModel.get_instance().getGame(gameID);
    }

    /**
     * gets the list of commands from the ServerModel that have not been executed yet by the client
     * @pre client is
     * @post
     * @param client the player that is checking for updates
     * @param gameID the ID of the game to check for updates from
     * @param lastUpdate the ID of the last command the client recieved
     * @return returns a list of commands to be executed on the client side, returns null if all commands are up to date
     */
    public List<ICommand> getGameUpdates(Player client, int gameID, int lastUpdate) {
        return ServerModel.get_instance().getGameUpdates(client, gameID, lastUpdate);
    }

    /**
     * gets a GameDescription from the ServerModel of the given gameID
     * @pre username is valid and password is valid
     * @post
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the GameDescriptionn of the given gameID, null if the game does not exist
     */
    public GameDescription getGameDescription(String username, String password, int gameID) {
        return ServerModel.get_instance().getGameDescription(gameID);
    }

    /**
     * checks that the player exists and the password given matches the player's password
     * @pre username is valid and password is valid
     * @post
     * @param username the username of the player
     * @param password the password given by the user
     * @return true if the player exists and the input password matches the player's password,
     * returns false otherwise
     */
    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }
}
