package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameDescriptionCommand;
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
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
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


    /** If the user excists, and the password matches the server's user password, return that player. Else return null.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @param username The name of the user to check for login.
     * @param password The password of the user to check for login.
     * @return A player reference to the user.
     */
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

    /** Registers a new user into the server.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @param username The name of the user to be registered to the user.
     * @param password The password of the user to be registered to the user.
     * @return A player reference to the newly registered user.
     */
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

    /** Creates a new game to add to the list of games.
     * @pre hostPlayer is valid player.
     * @pre numOfPlayers is an int between 2 and 5.
     * @pre game name is 15 characters or less.
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @param hostPlayer The person creating/hosting the game.
     * @param numOfPlayers The maximum amount of players allowed to be in this game.
     * @param gameName The name for the game.
     * @param hostColor The color to be assigned to the host's player.
     * @return An instance of a game, with the specified fields.
     */
    @Override
    public GameDescription createGame(Player hostPlayer, int numOfPlayers, String gameName, String hostColor) {
        //List<GameDescription> gameList = getGames(hostPlayer.getName(), hostPlayer.getPassword());
        //int gameID = gameList.size() + 1;

//        baseCMD = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers);
        ICommand cmd = new ClientCreateGameCommand(hostPlayer.getName(), hostPlayer.getPassword(), gameName, numOfPlayers, hostColor);
        Result result = myCC.executeCommandOnServer(cmd);
        System.out.println("in create game:");
        System.out.println(result.getRawSerializedResult());
        return (GameDescription) result.toClass(GameDescription.class);
    }

    /** Join a player into a game that is already created.
     * @pre newPlayer is a valid player.
     * @pre gameId is a non negative int.
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @param newPlayer The player to be added to the game.
     * @param gameID the id of the game to add the player to.
     * @param color the color to assign to the player in the game.
     * @return An instance of a game, with the specified fields.
     */
    @Override
    public GameDescription joinGame(Player newPlayer, int gameID, String color) {
//        baseCMD = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID);
        ICommand cmd = new ClientJoinGameCommand(newPlayer.getName(), newPlayer.getPassword(), gameID, color);
        Result result = myCC.executeCommandOnServer(cmd);
        System.out.println("in join game:");
        System.out.println(result.getRawSerializedResult());
        return (GameDescription) result.toClass(GameDescription.class);
    }

    /** Returns a list of games that a specified player is currently in. If the person doesn't excist, or wrong password, return null.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @param username The username of the person we wish to view games for.
     * @param password The password of the person we wish to view games for.
     * @return A list of games for the specified person.
     */
    @Override
    public GameDescriptionHolder getGames(String username, String password) {
//        baseCMD = new ClientGetGameListCommand(username, password);
        ICommand cmd = new ClientGetGameListCommand(username, password);
        Result result = myCC.executeCommandOnServer(cmd);
        System.out.println(result.getRawSerializedResult());
        return (GameDescriptionHolder) result.toClass(GameDescriptionHolder.class);
    }

    /** Returns a game description given a user in the game and the id.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param username The person inside the game.
     * @param password The password of the person inside the game.
     * @param gameID The game description id that is to be viewed.
     * @return A game description.
     */
    @Override
    public GameDescription getGameDescription(String username, String password, int gameID) {
        ICommand cmd = new ClientGetGameDescriptionCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        return (GameDescription) result.toClass(GameDescription.class);
    }

    /** Returns a list of players that are currently inside a game.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param username The user of a game.
     * @param password The password of the user.
     * @param gameID The id of a game we are to view.
     * @return A list of current players.
     */
    @Override
    public List<Player> getLatestPlayers(String username, String password, int gameID) {

        ICommand cmd = new ClientGetGameDescriptionCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        GameDescription gameDescription = (GameDescription) result.toClass(GameDescription.class);
        return gameDescription.getPlayers();
    }

    /** Returns a game given a user and an id.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param username A person inside the game.
     * @param password The password of person inisde the game.
     * @param gameID The game id that is to be viewed.
     * @return A game
     */
    @Override
    public Game getGame(String username, String password, int gameID) {

        ICommand cmd = new ClientGetGameCommand(username, password, gameID);
        Result result = myCC.executeCommandOnServer(cmd);
        return (Game) result.toClass(Game.class);
    }

    /** Returns a list of current commands on the game back to the user.
     * @pre player is a valid player.
     * @pre gameId is a non negative int.
     * @pre LastUpdate is a non negative int.
     * @param player The player iside the game.
     * @param gameID The id of the game the player is in.
     * @param lastUpdate An index of the last command that the player executed.
     * @return A list of commands.
     */
    @Override
    public CommandHolder getGameCommands(Player player, int gameID, int lastUpdate) {
        ICommand cmd = new ClientGetGameUpdatesCommand(player.getName(), player.getPassword(), gameID, lastUpdate);
        Result result = myCC.executeCommandOnServer(cmd);
        return (CommandHolder) result.toClass(CommandHolder.class); //TODO: fix
    }
}
