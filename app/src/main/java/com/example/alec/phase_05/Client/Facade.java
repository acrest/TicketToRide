package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private static Facade _instance;
    private ServerProxy proxy = null;
    private boolean set = false;
    /**
     * lets other methods know if the poller has already started.
     */
    public boolean pollerStarted = false;
    /**
     * gets the instance of the Singleton poller if there is on, if not creates one.
     */
    Poller poller = Poller.getInstance();


    /**
     * constructor that starts with a new Server Proxy
     */
    public Facade() {
        proxy = new ServerProxy(null, null);
    }

    /**
     * gets singleton instance of Facade.
     * @return current instance of Facade, if not creates one.
     */
    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    /**
     * Uses the username and password to login if valid player through ServerProxy.
     * Also starts the poller.
     * @param username the name of the player that is trying to login
     * @param password the password of the player that is trying to login
     * @return true if the player was successfully logged in, if not returns false.
     */
    public boolean login(final String username, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = proxy.login(username, password);
                if(setCurrentPlayer(player))
                {
                    poller.setListGamePolling();
                    setSet(true);
                }
            }
        });
        thread.start();
        try {
            thread.join();
            if(!pollerStarted) {
                poller.start();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(set == true)
        {
            setSet(false);
            return true;
        }

        return false;
    }

    /**
     * Register's user and automatically logs them in through ServerProxy.
     * Also starts the poller.
     * @param username the name of the player trying to register
     * @param password the password of the player trying to register
     * @return true if registration was successful false if not.
     */
    public boolean registerUser(final String username, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = proxy.registerUser(username, password);

                if(setCurrentPlayer(player))
                {
                    poller.setListGamePolling();
                    setSet(true);
                }
            }
        });
        thread.start();
        try {
            thread.join();
            if(!pollerStarted) {
                poller.start();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(set == true)
        {
            setSet(false);
            return true;
        }

        return false;
    }

    /**
     * Creates a new thread.
     * Creates game after calling the method in ServerProxy.
     * Starts the player waiting poller.
     * @param numOfPlayers the number of players the hostPlayer sets
     * @param gameName the name of the game that is given automatically,
     *                 but can be named differently by the host player.
     * @param hostColor the color that the hostPlayer chooses for their
     *                  pawn in the game
     * This creates a thread which initializes the game and waits for
     *                  more players to join.
     */
    public void createGame(final int numOfPlayers, final String gameName, final String hostColor) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                GameDescription newGame = proxy.createGame(player, numOfPlayers, gameName, hostColor);
                ClientModel.getInstance().createGame(newGame);
            }
        });
        thread.start();
        try {
            thread.join();
            poller.setPlayerWatingPolling();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows player to join a game that has not started yet.
     * Starts player waiting poller.
     * @param gameID the id of the game the player desires to join.
     * @param color the color that the current player chooses for
     *              their pawn in the game.
     * This creates a thread that waits with the poller for more
     *              players to join the game and then starts the game
     */
    public void joinGame(final int gameID, final String color) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                GameDescription joinedGame = proxy.joinGame(player, gameID, color);
                ClientModel.getInstance().joinGame(joinedGame);
            }
        });
        thread.start();
        try {
            thread.join();
            poller.setPlayerWatingPolling();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Call to get the games in the Game Station
     * @param player name of player who's client is trying to get the list of games in the GameLobby
     */
    public void getGames(final Player player) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameDescriptionHolder holder = proxy.getGames(player.getName(), player.getPassword());
                ClientFacade.getInstance().updateGameList(holder.getGameDescriptions());
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the game, specifically the current game state, that the player is in
     * @param player the player class of the user. Includes their username
     *               and password which get passed when getting calling the
     *               ServerProxy to get the current game state
     * @param gameID the id of the game that we are finding the state of.
     */
    public void getGame(final Player player, final int gameID) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Game game = proxy.getGame(player.getName(), player.getPassword(), gameID);
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void removeGame(final int gameID){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                proxy.removeGame(gameID);
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//            poller.setPlayerWatingPolling(); //I don't really know.
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Call that sees if there are any changes in the game
     * and then will update the changes once implemented.
     */
    public void updateGameChanges() {
        ClientModel cl = ClientModel.getInstance();
        Game game = cl.getCurrentGame();
        if(game != null) {
            //TODO: implement this
            //proxy.getGameCommands(cl.getCurrentPlayer(), game.getID(), cl.getLastUpdate());
        }
    }

    /**
     * Sets the player as the current player
     * @param player the player that is the current player
     * @return true if the setCurrentPlayer was successful
     * or false otherwise.
     */
    private boolean setCurrentPlayer(Player player)
    {
        if(player != null)
        {
            ClientModel.getInstance().setCurrentPlayer(player);
            return true;
        }

        return false;
    }

    /**
     * sets the private variable 'set' to either true or false
     * @param switchTo the boolean to set the variable 'set'
     */
    private void setSet(boolean switchTo)
    {
        this.set = switchTo;
    }
}
