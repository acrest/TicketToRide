package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerModel {
    private static int nextValidGameID = 0;

    private Map<Integer,GameState> gamesMap;
    private Map<String,Player> playerMap;

    private static ServerModel _instance;


    /**
     * Generates new ServerModel, no parameters or return
     */
    public ServerModel() {
        playerMap = new HashMap<>();
        gamesMap = new HashMap<>();
    }

    /**
     * Gets instance of ServerModel if exists, if not creates
     * @return the instance of the ServerModel
     */
    public static ServerModel get_instance(){
        if(_instance == null){
            _instance = new ServerModel();
        }
        return _instance;
    }

    /**
     * Checks when adding new player to see if it already exists
     * Adds to the playerMap
     * @param newPlayer Player to be added to the server
     * @return boolean if the player was added successfully
     */
    public boolean addPlayer(Player newPlayer){
        String playerName = newPlayer.getName();
        if(playerMap.containsKey(playerName))
            return false;
        playerMap.put(playerName,newPlayer);
        return true;
    }

    /**
     * Checks to see if the game already exists by ID, if
     * it doesn't exist, it adds it to the gamesMap
     * @param gameInfo GameDesciription object of new game created
     * @return boolean if the game was added successfully
     */
    public boolean createGame(GameDescription gameInfo){
        int gameID = gameInfo.getID();
        if(gamesMap.containsKey(gameID))
            return false;
        gamesMap.put(gameID,new GameState(gameInfo));
        return true;
    }

    /**
     * Returns game by ID
     * @param gameID of Game object to be returned
     * @return Game object with the ID given in the parameter
     */
    public GameState getGame(Integer gameID){
        return gamesMap.get(gameID);
    }

    /**
     *Returns player by name
     * @param playerName name of player to be returned
     * @return player object with name given in the parameter
     */
    public Player getPlayer(String playerName){
        return playerMap.get(playerName);
    }

    /**
     * Checks the login to make sure player password combination
     * exists in the playerMap.
     * @param inputPass password given by the user
     * @param playerName name given by the user
     * @return returns the player object if password and player combination are correct
     * if they are not correct, or they don't exist, it returns null.
     */
    public Player login(String inputPass, String playerName){
        Player player = playerMap.get(playerName);
        if(player == null) return null;
        if(player.getPassword().equals(inputPass)){
            return player;
        }
        else{
            return null;
        }
    }

    /**
     * Gives a list with all the games available to join
     * @return list of available games, meaning games created but not full or started yet.
     */
    public List<GameDescription> getGameDescriptions() {
        List<GameDescription> gameDescriptions = new ArrayList<>();
        for (GameState gameState : gamesMap.values()) {
            if (gameState.getMaxPlayers() != gameState.getNumberPlayers()){
                gameDescriptions.add(gameState.getGameDescription());
            }

        }
        return gameDescriptions;
    }

    /**
     * Gets game description by gameID
     * @param gameID of gameDescription to be returned
     * @return gameDescription of game with ID given in parameters
     */
    public GameDescription getGameDescription(int gameID) {
        if(!gamesMap.containsKey(gameID)) {
            return null;
        }
        return gamesMap.get(gameID).getGameDescription();
    }

    /**
     * gets the list of commands that haven't been sent to client
     * @param player name of player whose poller is getting the update for
     * @param gameID id of game for which the player is participating in
     * @param lastUpdate the last command's ID so we can see how many commands have been
     *                   created since the player has had a command executed
     * @return list of commands that haven't been executed yet for given player and game,
     * or null if there are no commands to be executed.
     */
    public List<ICommand> getGameUpdates(Player player, int gameID, int lastUpdate) {
        //TODO: implement this
        return null;
    }
}
