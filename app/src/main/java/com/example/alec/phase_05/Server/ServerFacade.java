package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Client.Model.ClientModel;
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

    public List<GameDescription> getGames(String username, String password) {
        //{"password":"55","userName":"andrew","commandName":"GetGameList"}
        ServerModel model = ServerModel.get_instance();
        return model.getGameDescriptions();
    }

    public Game getGame(String username, String password, int gameID) {
        return ServerModel.get_instance().getGame(gameID);
    }

    public List<BaseCommand> getGameUpdates(Player client, int gameID) {
        return ServerModel.get_instance().getGameUpdates(client, gameID);
    }

    public GameDescription getGameDescription(String username, String password, int gameID) {
        return ServerModel.get_instance().getGameDescription(gameID);
    }

    public CommandHolder getCommandsForClient(String username, String password, int gameID) {
        ServerModel model = ServerModel.get_instance();
        ServerGame game = model.getGame(gameID);
        List<BaseCommand> commands = game.getCommandManager().recentCommands(new Player(username, password));
        return new CommandHolder(commands);
    }

    public GameState getGameState(String username, String password, int gameID) {
        IServerGame game = ServerModel.get_instance().getGame(gameID);
        return GameStateFactory.gameToGameState(game);
    }

    public TrainCard drawTrainCard(String username, String password, int gameID, int index) {
        ServerModel model = ServerModel.get_instance();
        IServerGame game = model.getGame(gameID);
        if(game == null) return null;
        IServerBank bank = (IServerBank) game.getBank();
        return bank.drawTrainCard();
    }

    public DestinationCard drawDestinationCard(String username, String password, int gameID) {
        ServerModel model = ServerModel.get_instance();
        IServerGame game = model.getGame(gameID);
        if(game == null) return null;
        IServerBank bank = (IServerBank) game.getBank();
        return bank.drawDestinationCard();
    }

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

    public boolean startGame(int gameId) {
        IServerGame game = ServerModel.get_instance().getGame(gameId);
        if(game == null) return false;
        game.setGameStarted();
        return true;
    }

    public boolean validateAuthentication(String username, String password) {
        Player player = ServerModel.get_instance().getPlayer(username);
        return player != null && player.getPassword().equals(password);
    }
}