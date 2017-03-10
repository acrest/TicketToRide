package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Server.command.ServerGameStartedCommand;
import com.example.alec.phase_05.Server.command.ServerResult;
import com.example.alec.phase_05.Server.command.ServerStartGameCommand;
import com.example.alec.phase_05.Server.model.GameStateFactory;
import com.example.alec.phase_05.Server.model.IServerBank;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.CommandHolder;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.IBank;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.List;


/**
 * Handles the communication between the ServerModel and the CommandHandler
 */
public class ServerFacade {
    private static ServerFacade _instance;


    public ServerFacade() {

    }

    /**
     * Returns the singleton instance of the ServerFacade and creates one if it has not been created
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
     * @pre username is not null. Username characters are limited to letters, ^, *,  and _.
     * @param username the username of the player to find
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
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @post the player successfully logs in if username and password match a registered user
     * @param username the username given by the user
     * @param password the password given by the user
     * @return returns the player if the password and username are correct, returns null if they are not correct
     */
    public Player login(String username, String password) {
        return ServerModel.get_instance().login(password, username);
    }

    /**
     * creates a new player using the given username and password and adds it to the ServerModel
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @post the user is registered if the user does not already exist
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
     * @pre hostPlayer is a valid Player obj.
     * @pre numOfPlayers is an int between 2 and 5.
     * @pre gameName is 15 characters or less.
     * @pre hostColor is "red", "blue", "yellow", "Black", or "green".
     * @post a new game is created with the variables from the input
     * @param hostPlayer the player who created the game
     * @param numOfPlayers the max number of players allowed in the game
     * @param gameName the name of the game
     * @param hostColor the color of the hostPlayer
     * @return the game state of the newly created game
     */
    public GameState createGame(Player hostPlayer, int numOfPlayers, String gameName, String hostColor) {
        //    {"gameName":"Hillary Clinton","hostColor":"green","numberOfPlayers":4,"password":"55","userName":"andrew","commandName":"CreateGame"}     example requestbody. Used for fake client.
          ServerModel model = ServerModel.get_instance();
//        Player[] players = new Player[numOfPlayers];
//        String[] colors = new String[numOfPlayers];
//        players[0] = hostPlayer;
//        colors[0] = hostColor;

        IServerGame game = model.createGame(gameName, numOfPlayers);
        int position = game.addPlayerAtNextPosition(hostPlayer);
        if(position == -1)
            return null;
        hostPlayer.setColor(hostColor);
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * adds a player to an existing game inside the ServerModel
     * @pre newPlayer is a valid Player obj
     * @pre gameId is a non negative int
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @post the player is added to the game if the game exists and the player is not already in it
     * @param newPlayer the player to be added to the game
     * @param gameID the ID of the game to be added to
     * @param color the chosen color of the newPlayer
     * @return returns the GameState if it exists, null if it does not exist or the player is already in the game
     */
    public GameState joinGame(Player newPlayer, int gameID, String color) {
        ServerModel model = ServerModel.get_instance();
        IServerGame game = model.getGame(gameID);
        newPlayer.setColor(color);
        if(game == null) return null;
        int playerPosition = game.addPlayerAtNextPosition(newPlayer);
        //check to see of the player was added successfully
        if(playerPosition == -1) return null;
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * gets the list of games from the ServerModel that are available to join
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
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
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the GameState of the given gameID, returns null if the game does not exist
     */
    public Game getGame(String username, String password, int gameID) {
        return ServerModel.get_instance().getGame(gameID);
    }

    /**
     * gets the list of commands from the ServerModel that have not been executed yet by the client
     * @pre the clientName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @param clientName the username of the player that is checking for updates
     * @param gameID the ID of the game to check for updates from
     * @return returns a list of commands to be executed on the client side, returns null if all commands are up to date
     */
    public List<BaseCommand> getGameUpdates(String clientName, int gameID) {
        return ServerModel.get_instance().getGameUpdates(clientName, gameID);
    }

    /**
     * gets a GameDescription from the ServerModel of the given gameID
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the GameDescriptionn of the given gameID, null if the game does not exist
     */
    public GameDescription getGameDescription(String username, String password, int gameID) {
        return ServerModel.get_instance().getGameDescription(gameID);
    }

    /**
     * returns the commandHolder for the given player
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the commandHolder for the player, null if player or game does not exist
     */
    public CommandHolder getCommandsForClient(String username, String password, int gameID) {
        ServerModel model = ServerModel.get_instance();
        ServerGame game = model.getGame(gameID);
        List<BaseCommand> commands = game.getCommandManager().recentCommands(username);
        return new CommandHolder(commands);
    }

    /**
     * gets a GameState from the ServerModel with the given gameID
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the GameState of the given gameID, null if the game does not exist
     */
    public GameState getGameState(String username, String password, int gameID) {
        IServerGame game = ServerModel.get_instance().getGame(gameID);
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * draws a train card for the current player
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @pre index is -1 through 4
     * @post the player recieves a train card and the trainCard deck is decremented
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @param index the index of the visible card to draw or -1 if drawing from bank
     * @return a trainCard if there are enough, null otherwise
     */
    public TrainCard drawTrainCard(String username, String password, int gameID, int index) {
        ServerModel model = ServerModel.get_instance();
        IServerGame game = model.getGame(gameID);
        if(game == null) return null;
        IServerBank bank = (IServerBank) game.getBank();
        if(index == -1) {
            return bank.drawTrainCard();
        } else {
            return bank.drawVisibleTrainCard(index);
        }
    }

    /**
     * draws a destination card for the current player
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @post the player recieves a train card and the trainCard deck is decremented
     * @param username the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return a destination card if there are enough, null otherwise
     */
    public DestinationCard drawDestinationCard(String username, String password, int gameID) {
        ServerModel model = ServerModel.get_instance();
        IServerGame game = model.getGame(gameID);
        if(game == null) return null;
        IServerBank bank = (IServerBank) game.getBank();
        return bank.drawDestinationCard();
    }

    /**
     * @pre a valid command object
     * @post the commmand is executed
     * @param command the command to be executed
     * @return the results of the executed command
     */
    public Result executeCommand(ICommand command) {
        ServerModel model = ServerModel.get_instance();
        Result r;
        if(command == null) {
            r = new ServerResult();
            r.setErrorMessage("no server command to match command sent to server");
        }
        if(command instanceof GameCommand) {
            GameCommand gameCommand = (GameCommand) command;
            ServerGame game = model.getGame(gameCommand.getGameID());
            game.getCommandManager().addCommand(gameCommand);
            r = gameCommand.execute();
        } else {
            r = command.execute();
        }
        return r;
    }

    /**
     * starts the game on the server
     * @pre gameId is a non negative int
     * @param gameId the id of the game
     * @return true if the gameID is valid, false otherwise
     */
    public boolean startGame(int gameId) {
        IServerGame game = ServerModel.get_instance().getGame(gameId);
        if(game == null) return false;
        game.setGameStarted();
        return true;
    }

    /**
     * checks that the player exists and the password given matches the player's password
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @param username the username of the player
     * @param password the password given by the user
     * @return true if the player exists and the input password matches the player's password,
     * returns false otherwise
     */
    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }

    /**
     * returns an unwanted destination card to the deck
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @pre DestinationCard is not null
     * @post the returned destinationCard is added to the deck and removed from the player's hand
     * @param playerName the username of the player
     * @param gameId the id of the game
     * @param card the card to be returned
     * @return true if the game exists, false otherwise
     */
    public boolean putBackDestinationCard(String playerName, int gameId, DestinationCard card) {
        IServerGame game = ServerModel.get_instance().getGame(gameId);
        if(game == null) return false;
        IServerBank bank = (IServerBank) game;
        //bank.addDestinationCardToBottom( , card); //TODO: not sure what the id is
        return true;
    }

    /**
     * discards a traincard
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @pre TrainCard is not null
     * @post the discarded destinationCard is added to the discard deck and removed from the player's hand
     * @param playerName the username of the player
     * @param gameId the id of the game
     * @param card the card to be returned
     * @return true if the game exists, false otherwise
     */
    public boolean discardTrainCard(String playerName, int gameId, TrainCard card) {
        IServerGame game = ServerModel.get_instance().getGame(gameId);
        if(game == null) return false;
        IServerBank bank = (IServerBank) game;
        //bank.d( , card); //TODO
        return true;
    }

    /**
     * gets the serverGameStartedCommand
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @param playerName the username of the player
     * @param gameId the id of the game
     * @return the ServerGameStartedCommand
     */
    public ServerGameStartedCommand getGameStartedCommand(String playerName, int gameId) {
        IServerGame game = ServerModel.get_instance().getGame(gameId);
        if(game == null) return null;
        List<BaseCommand> commands = game.getCommandManager().recentCommands(playerName);
        if(commands.size() > 0) {
            ServerGameStartedCommand command = (ServerGameStartedCommand) commands.get(0);
            return command;
        } else {
            return null;
        }
    }
}