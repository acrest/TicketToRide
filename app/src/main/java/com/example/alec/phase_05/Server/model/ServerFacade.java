package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Server.command.ServerResult;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.IPlayer;
import com.example.alec.phase_05.Shared.model.IServer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.PlayerCredentials;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.TrainCard;

import java.util.Iterator;
import java.util.List;


/**
 * Handles the communication between the ServerModel and the CommandHandler
 */
public class ServerFacade implements IServer {
    private static ServerFacade instance;

    /**
     * Returns the singleton instance of the ServerFacade and creates one if it has not been created
     *
     * @return current instance of ServerFacade
     */
    public static ServerFacade getInstance() {
        if (instance == null) {
            instance = new ServerFacade();
        }
        return instance;
    }

    private ServerModel model;

    public ServerFacade() {
        model = ServerModel.getInstance();
    }

    /**
     * @param command the command to be executed
     * @return the results of the executed command
     * @pre a valid command object
     * @post the commmand is executed
     */
    @Override
    public Result executeCommand(ICommand command) {
        Result r;
        if (command == null) {
            r = new ServerResult();
            r.setErrorMessage("no server command to match command sent to server");
        }
        if (command instanceof GameCommand) {
            GameCommand gameCommand = (GameCommand) command;
            ServerGame game = model.getGame(gameCommand.getGameId());
            if (game != null) {
                game.addCommand(gameCommand);
            }
            r = gameCommand.execute();
        } else {
            r = command.execute();
        }
        return r;
    }

    /**
     * Finds the specific player inside the ServerModel
     * @pre username is not null. Username characters are limited to letters, ^, *,  and _.
     * @param username the username of the player to find
     * @return the player if he or she exists
     */
//    public Player getPlayerByName(String username)
//    {
//        return ServerModel.getInstance().getPlayer(username);
//    }

    /**
     * returns false
     *
     * @return false
     */
    public boolean poller() {
        return false;
    }

    /**
     * calls the login method inside ServerModel
     *
     * @param username the username given by the user
     * @param password the password given by the user
     * @return returns the player if the password and username are correct, returns null if they are not correct
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @post the player successfully logs in if username and password match a registered user
     */
    @Override
    public boolean login(String username, String password) {
        return model.login(password, username);
    }

    /**
     * creates a new player using the given username and password and adds it to the ServerModel
     *
     * @param username the username given by the user
     * @param password the password given by the user
     * @return returns false if the player already exists, true otherwise
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @post the user is registered if the user does not already exist
     */
    @Override
    public boolean registerUser(String username, String password) {
        PlayerCredentials player = new PlayerCredentials(username, password);
        System.out.println("TEST RESGISTER USER " + player.getUsername() + " " + player.getPassword());
        return model.addPlayer(player);
    }

    /**
     * creates a new game and adds it to the ServerModel
     *
     * @param playerName   the player who created the game
     * @param numOfPlayers the max number of players allowed in the game
     * @param gameName     the name of the game
     * @param hostColor    the color of the hostPlayer
     * @return the game state of the newly created game
     * @pre hostPlayer is a valid Player obj.
     * @pre numOfPlayers is an int between 2 and 5.
     * @pre gameName is 15 characters or less.
     * @pre hostColor is "red", "blue", "yellow", "Black", or "green".
     * @post a new game is created with the variables from the input
     */
    @Override
    public GameInfo createGame(String playerName, int numOfPlayers, String gameName, String hostColor) {
        //    {"gameName":"Hillary Clinton","hostColor":"green","numberOfPlayers":4,"password":"55","userName":"andrew","commandName":"CreateGame"}     example requestbody. Used for fake client.
//        Player[] players = new Player[numOfPlayers];
//        String[] colors = new String[numOfPlayers];
//        players[0] = hostPlayer;
//        colors[0] = hostColor;

        IServerGame game = model.createGame(gameName, numOfPlayers);
        Player player = new Player(playerName);
        player.setColor(hostColor);
        int position = game.addPlayerAtNextPosition(player);
        if (position == -1)
            return null;
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * adds a player to an existing game inside the ServerModel
     *
     * @param playerName the player to be added to the game
     * @param gameID     the ID of the game to be added to
     * @param color      the chosen color of the newPlayer
     * @return returns the GameInfo if it exists, null if it does not exist or the player is already in the game
     * @pre newPlayer is a valid Player obj
     * @pre gameId is a non negative int
     * @pre host color is either "red", "blue", "yellow", "Black", or "green".
     * @post the player is added to the game if the game exists and the player is not already in it
     */
    @Override
    public GameInfo joinGame(String playerName, int gameID, String color) {
        IServerGame game = model.getGame(gameID);
        Player player = new Player(playerName);
        player.setColor(color);
        if (game == null) return null;
        int playerPosition = game.addPlayerAtNextPosition(player);
        //check to see of the player was added successfully
        if (playerPosition == -1) return null;
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * gets the list of games from the ServerModel that are available to join
     *
     * @return a list of available games to join
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     */
    @Override
    public List<GameDescription> getGames() {
        //{"password":"55","userName":"andrew","commandName":"GetGameList"}
        return model.getGameDescriptions();
    }

    /**
     * gets a specific GameInfo based on the given gameID
     *
     * @param gameID the ID of the game
     * @return the GameInfo of the given gameID, returns null if the game does not exist
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     */
    @Override
    public Game getGame(int gameID) {
        return model.getGame(gameID);
    }

    /**
     * gets the list of commands from the ServerModel that have not been executed yet by the client
     *
     * @param gameID the ID of the game to check for updates from
     * @return returns a list of commands to be executed on the client side, returns null if all commands are up to date
     * @pre the clientName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     */
    @Override
    public ICommand getNextCommand(String playerName, int gameID) {
        return model.getNextCommand(playerName, gameID);
    }

    /**
     * gets a GameDescription from the ServerModel of the given gameID
     *
     * @param gameID the ID of the game
     * @return the GameDescriptionn of the given gameID, null if the game does not exist
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     */
    @Override
    public GameDescription getGameDescription(int gameID) {
        return model.getGameDescription(gameID);
    }

    /**
     * returns the commandHolder for the given player
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @param playerName the username of the player
     * @param password the password of the player
     * @param gameID the ID of the game
     * @return the commandHolder for the player, null if player or game does not exist
     */
//    @Override
//    public CommandHolder getCommandsForClient(String playerName, int gameID) {
//        ServerModel model = ServerModel.getInstance();
//        ServerGame game = model.getGame(gameID);
//        List<BaseCommand> commands = game.getCommandManager().recentCommands(playerName);
//        return new CommandHolder(commands);
//    }

    /**
     * gets a GameInfo from the ServerModel with the given gameID
     *
     * @param gameID the ID of the game
     * @return the GameInfo of the given gameID, null if the game does not exist
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     */
    @Override
    public GameInfo getGameState(int gameID) {
        IServerGame game = model.getGame(gameID);
        return GameStateFactory.gameToGameState(game);
    }

    /**
     * draws a train card for the current player
     *
     * @param playerName the username of the player
     * @param gameID     the ID of the game
     * @param index      the index of the visible card to draw or -1 if drawing from bank
     * @return a trainCard if there are enough, null otherwise
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @pre index is -1 through 4
     * @post the player recieves a train card and the trainCard deck is decremented
     */
    @Override
    public TrainCard pickTrainCard(String playerName, int gameID, int index) {
        IServerGame game = model.getGame(gameID);
        if (game == null) return null;
        return game.pickTrainCard(playerName, index);
    }

    @Override
    public TrainCard drawTrainCard(String playerName, int gameId) {
        IServerGame game = model.getGame(gameId);
        if (game == null) return null;
        return game.drawTrainCard(playerName);
    }

    /**
     * draws a destination card for the current player
     *
     * @param gameID the ID of the game
     * @return a destination card if there are enough, null otherwise
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @pre gameId is a non negative int
     * @post the player recieves a train card and the trainCard deck is decremented
     */
    @Override
    public DestinationCard drawDestinationCard(String playerName, int gameID) {
        IServerGame game = model.getGame(gameID);
        if (game == null) return null;
        return game.drawDestinationCard(playerName);
    }

    /**
     * starts the game on the server
     *
     * @param gameId the id of the game
     * @return true if the gameID is valid, false otherwise
     * @pre gameId is a non negative int
     */
    @Override
    public boolean startGame(int gameId) {
        IServerGame game = model.getGame(gameId);
        if (game == null) return false;
        game.setGameStarted();
        return true;
    }

    @Override
    public boolean finishTurn(String playerName, int gameId) {
        return false; //TODO
    }

    /**
     * returns an unwanted destination card to the deck
     *
     * @param playerName the username of the player
     * @param gameId     the id of the game
     * @param card       the card to be returned
     * @return true if the game exists, false otherwise
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @pre DestinationCard is not null
     * @post the returned destinationCard is added to the deck and removed from the player's hand
     */
    @Override
    public boolean returnDestinationCard(String playerName, int gameId, DestinationCard card) {
        IServerGame game = model.getGame(gameId);
        if (game == null) return false;
        IServerBank bank = (IServerBank) game;
        bank.addDestinationCardToBottom(card);
        return true;
    }

    /**
     * discards a traincard
     *
     * @param playerName the username of the player
     * @param gameId     the id of the game
     * @param card       the card to be returned
     * @return true if the game exists, false otherwise
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @pre TrainCard is not null
     * @post the discarded destinationCard is added to the discard deck and removed from the player's hand
     */
    @Override
    public boolean discardTrainCard(String playerName, int gameId, TrainCard card) {
        IServerGame game = model.getGame(gameId);
        if (game == null) return false;
        IServerBank bank = (IServerBank) game;
        return true;
    }

    @Override
    public boolean claimRoute(String playerName, int gameId, int routeId) {
        IServerGame game = model.getGame(gameId);
        if (game == null) return false;
        Player player = (Player) game.getPlayerByName(playerName);
        if (player == null) return false;
        Route route = game.getRouteByID(routeId);
        int count = route.getLength();
        Iterator<TrainCard> cards = player.getTrainCards().iterator();
        while(count > 0) {
            if(cards.next().getType().equals(route.getType())) {
                count--;
            }
        }
        if(count > 0) return false;
        count = route.getLength();
        cards = player.getTrainCards().iterator();
        while(count > 0 && cards.hasNext()) {
            TrainCard card = cards.next();
            if(card.getType().equals(route.getType())) {
                count--;
                cards.remove();
            }
        }
        route.setOwner(player);
        return true;
    }

    @Override
    public List<IPlayer> getLatestPlayers(int gameID) {
        return null;
    }

//    public ServerGetGameStartedCommand getGameStartedCommand(String playerName, int gameId) {
//        return null;
//    }

    /**
     * gets the serverGameStartedCommand
     * @pre playerName is not null, username characters are limited to letters, ^, *,  and _
     * @pre gameId is a non negative int
     * @param playerName the username of the player
     * @param gameId the id of the game
     * @return the ServerGameStartedCommand
     */
//    public ServerGameStartedCommand getGameStartedCommand(String playerName, int gameId) {
//        IServerGame game = ServerModel.getInstance().getGame(gameId);
//        if(game == null) return null;
//        List<BaseCommand> commands = game.getCommandManager().recentCommands(playerName);
//        if(commands.size() > 0) {
//            ServerGameStartedCommand command = (ServerGameStartedCommand) commands.get(0);
//            return command;
//        } else {
//            return null;
//        }
//    }

    /**
     * checks that the player exists and the password given matches the player's password
     * @pre Username is not null, username characters are limited to letters, ^, *,  and _
     * @pre Password is not null, password characters rare limited to letters, numbers, ^, *, and _
     * @param username the username of the player
     * @param password the password given by the user
     * @return true if the player exists and the input password matches the player's password,
     * returns false otherwise
     */
//    public boolean validateAuthentication(String username, String password) {
//        Player player = ServerModel.getInstance().getPlayer(username);
//        return player != null && player.getPassword().equals(password);
//    }
}