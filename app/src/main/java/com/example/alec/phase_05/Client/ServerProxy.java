package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientClaimRouteCommand;
import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Client.command.ClientDiscardTrainCardCommand;
import com.example.alec.phase_05.Client.command.ClientDrawDestinationCardCommand;
import com.example.alec.phase_05.Client.command.ClientDrawTrainCardCommand;
import com.example.alec.phase_05.Client.command.ClientFinishGameCommand;
import com.example.alec.phase_05.Client.command.ClientFinishTurnCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameDescriptionCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameListCommand;
import com.example.alec.phase_05.Client.command.ClientGetGameStateCommand;
import com.example.alec.phase_05.Client.command.ClientGetNextChangeCommand;
import com.example.alec.phase_05.Client.command.ClientJoinGameCommand;
import com.example.alec.phase_05.Client.command.ClientLoginCommand;
import com.example.alec.phase_05.Client.command.ClientPickTrainCardCommand;
import com.example.alec.phase_05.Client.command.ClientRegisterCommand;
import com.example.alec.phase_05.Client.command.ClientReturnDestinationCardCommand;
import com.example.alec.phase_05.Client.command.ClientSendChatCommand;
import com.example.alec.phase_05.Client.command.ClientSetServerTrainCountCommand;
import com.example.alec.phase_05.Client.command.ClientStartGameCommand;
import com.example.alec.phase_05.Client.communication.ClientCommunicator;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.IServer;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;

/**
 * Created by clarkpathakis on 2/7/17.
 */

public class ServerProxy implements IServer {
    private static String DEFAULT_HOST = "localhost";
    private static String DEFAULT_PORT = "8080";
    private static IServer instance = null;

    public static IServer getInstance() {
        if(instance == null) {
            instance = new ServerProxy(DEFAULT_HOST, DEFAULT_PORT);
        }
        return instance;
    }

    private ClientCommunicator myCC;

    public ServerProxy(String serverHost, String serverPort) {
//        myHost = serverHost;
//        myPort = serverPort;
        myCC = ClientCommunicator.getInstance();
        if (serverHost == null) {
            //myCC.setServerIP(DEFAULT_HOST);
        } else {
            //myCC.setServerIP(serverHost);
        }
        if (serverPort == null) {
            myCC.setServerPort(DEFAULT_PORT);
        } else {
            myCC.setServerPort(serverPort);
        }
    }

    @Override
    public Result executeCommand(ICommand command) {
        return myCC.executeCommandOnServer(command);
    }

    /** If the user excists, and the password matches the server's user password, return that player. Else return null.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @param username The name of the user to check for login.
     * @param password The password of the user to check for login.
     * @return A player reference to the user.
     */
    @Override
    public boolean login(String username, String password) {
        Result result = executeCommand(new ClientLoginCommand(username, password));
        return result != null && result.toBoolean();
    }

    /** Registers a new user into the server.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @param username The name of the user to be registered to the user.
     * @param password The password of the user to be registered to the user.
     * @return A player reference to the newly registered user.
     */
    @Override
    public boolean registerUser(String username, String password) {
        Result result = executeCommand(new ClientRegisterCommand(username, password));
        return result != null && result.toBoolean();
    }

    /** Creates a new game to add to the list of games.
     * @pre hostPlayer is valid player.
     * @pre numOfPlayers is an int between 2 and 5.
     * @pre game name is 15 characters or less.
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @param playerName The person creating/hosting the game.
     * @param numOfPlayers The maximum amount of players allowed to be in this game.
     * @param gameName The name for the game.
     * @param hostColor The color to be assigned to the host's player.
     * @return An instance of a game, with the specified fields.
     */
    @Override
    public GameInfo createGame(String playerName, int numOfPlayers, String gameName, String hostColor) {
        System.out.println("inside server proxy create game");
        System.out.println("inside " + gameName + " " + numOfPlayers +  " " + playerName + " " + hostColor);
        return (GameInfo) executeCommand(new ClientCreateGameCommand(gameName, numOfPlayers, playerName, hostColor))
                .toClass(GameInfo.class);
    }

    /** Join a player into a game that is already created.
     * @pre newPlayer is a valid player.
     * @pre gameId is a non negative int.
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @param playerName The player to be added to the game.
     * @param gameID the id of the game to add the player to.
     * @param color the color to assign to the player in the game.
     * @return An instance of a game, with the specified fields.
     */
    @Override
    public GameInfo joinGame(String playerName, int gameID, String color) {
        System.out.println("inside join game server proxy");
        return (GameInfo) executeCommand(new ClientJoinGameCommand(playerName, gameID, color)).toClass(GameInfo.class);
    }

    /** Returns a list of games that a specified player is currently in. If the person doesn't excist, or wrong password, return null.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @return A list of games for the specified person.
     */
    @Override
    public List<GameDescription> getGames() {
        return ((GameDescriptionHolder) executeCommand(new ClientGetGameListCommand())
                .toClass(GameDescriptionHolder.class)).getGameDescriptions();
    }

    /** Returns a game description given a user in the game and the id.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param gameID The game description id that is to be viewed.
     * @return A game description.
     */
    @Override
    public GameDescription getGameDescription(int gameID) {
        return (GameDescription) executeCommand(new ClientGetGameDescriptionCommand(gameID)).toClass(GameDescription.class);
    }

    /** Returns a list of players that are currently inside a game.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param gameID The id of a game we are to view.
     * @return A list of current players.
     */
    @Override
    public List<IPlayer> getLatestPlayers(int gameID) {
        return null;
    }

    /** Returns a game given a user and an id.
     * @pre Username is not null. Username is characters restricted to letters, numbers, ^, *, _
     * @pre Password is not null. Password is characters restricted to letters, numbers, ^, *, _
     * @pre gameId is a non negative int.
     * @param gameID The game id that is to be viewed.
     * @return A game
     */
    @Override
    public Game getGame(int gameID) {
        return (Game) executeCommand(new ClientGetGameCommand(gameID)).toClass(Game.class);
    }

    /** Returns a list of current commands on the game back to the user.
     * @pre player is a valid player.
     * @pre gameId is a non negative int.
     * @pre LastUpdate is a non negative int.
     * @param gameID The id of the game the player is in.
     * @return A list of commands.
     */
//    @Override
//    public CommandHolder getGameCommands(Player player, int gameID) {
//        ICommand cmd = new ClientGetCommandListCommand(player.getName(), player.getPassword(), gameID);
//        Result result = executeCommand(command);
//        return (CommandHolder) result.toClass(CommandHolder.class);
//    }


    @Override
    public ICommand getNextCommand(String playerName, int gameID) {
        return executeCommand(new ClientGetNextChangeCommand(playerName, gameID)).toCommand();
    }

    @Override
    public GameInfo getGameState(int gameID) {
        return (GameInfo) executeCommand(new ClientGetGameStateCommand(gameID)).toClass(GameInfo.class);
    }

    //temporary method
//    public ClientGameStartedCommand getGameStartedCommand(String playerName, int gameId) {
//        return (ClientGameStartedCommand) executeCommand(new ClientGetGameStartedCommand(playerName, gameId))
//                .toClass(ClientGameStartedCommand.class);
//    }

    @Override
    public boolean claimRoute(String playerName, int gameID, int routeId) {
        return executeCommand(new ClientClaimRouteCommand(playerName, gameID, routeId)).toBoolean();
    }

    @Override
    public TrainCard drawTrainCard(String playerName, int gameId) {
        return (TrainCard) executeCommand(new ClientDrawTrainCardCommand(playerName, gameId)).toClass(TrainCard.class);
    }

    @Override
    public TrainCard pickTrainCard(String playerName, int gameId, int index) {
        return (TrainCard) executeCommand(new ClientPickTrainCardCommand(playerName, gameId, index)).toClass(TrainCard.class);
    }

    @Override
    public DestinationCard drawDestinationCard(String playerName, int gameId) {
        return (DestinationCard) executeCommand(new ClientDrawDestinationCardCommand(playerName, gameId)).toClass(DestinationCard.class);
    }

    @Override
    public boolean startGame(int gameId) {
        return executeCommand(new ClientStartGameCommand(gameId)).toBoolean();
    }

    @Override
    public boolean finishTurn(String playerName, int gameId) {
        return executeCommand(new ClientFinishTurnCommand(playerName, gameId)).toBoolean();
    }

    @Override
    public boolean finishGame(String playerName, int gameId) {
        return executeCommand(new ClientFinishGameCommand(playerName, gameId)).toBoolean();
    }

    @Override
    public boolean sendChat(Chat chat) {
        return executeCommand(new ClientSendChatCommand(chat)).toBoolean();
    }

    @Override
    public boolean setServerTrainCount(int count) {
        return executeCommand(new ClientSetServerTrainCountCommand(count)).toBoolean();
    }

    @Override
    public boolean returnDestinationCard(String playerName, int gameId, DestinationCard card){
        return executeCommand(new ClientReturnDestinationCardCommand(playerName, gameId, card)).toBoolean();
    }

    @Override
    public boolean discardTrainCard(String playerName, int gameId, TrainCard card) {
        return executeCommand(new ClientDiscardTrainCardCommand(playerName, gameId, card)).toBoolean();
    }
}
