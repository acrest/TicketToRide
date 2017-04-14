package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.TrainCard;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private static Facade _instance;

    /**
     * gets singleton instance of Facade.
     *
     * @return current instance of Facade, if not creates one.
     */
    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    private ClientModel model;
    private ClientFacade clientFacade;
    private ServerProxy proxy;
    private String ipAddress = "";
    private Integer trainCount = 0;

    public Integer getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(Integer trainCount) {
        this.trainCount = trainCount;
        setServerTrainCount(trainCount);
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    //    private boolean set = false;
    /**
     * lets other methods know if the poller has already started.
     */
//    public boolean pollerStarted = false;
    /**
     * gets the instance of the Singleton poller if there is on, if not creates one.
     */
    private Poller poller;


    /**
     * constructor that starts with a new Server Proxy
     */
    public Facade() {
        proxy = new ServerProxy(ipAddress, null);
        model = ClientModel.getInstance();
        clientFacade = ClientFacade.getInstance();
        poller = Poller.getInstance();
    }

    /**
     * Uses the username and password to login if valid player through ServerProxy.
     * Also starts the poller.
     *
     * @param username the name of the player that is trying to login
     * @param password the password of the player that is trying to login
     * @return true if the player was successfully logged in, if not returns false.
     */
    public void login(final String username, final String password) {
        System.out.println("in facade login");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientFacade.login(username, proxy.login(username, password));
            }
        });
        thread.start();
//        try {
//            thread.join();
//            if(!pollerStarted) {
//                poller.start();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if(set == true)
//        {
//            setSet(false);
//            return true;
//        }
//
//        return false;
    }

    /**
     * Register's user and automatically logs them in through ServerProxy.
     * Also starts the poller.
     *
     * @param username the name of the player trying to register
     * @param password the password of the player trying to register
     * @return true if registration was successful false if not.
     */
    public void registerUser(final String username, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientFacade.register(username, proxy.registerUser(username, password));
            }
        });
        thread.start();
//        try {
//            thread.join();
//            if(!pollerStarted) {
//                poller.start();
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if(set == true)
//        {
//            setSet(false);
//            return true;
//        }
//
//        return false;
    }

    /**
     * Creates a new thread.
     * Creates game after calling the method in ServerProxy.
     * Starts the player waiting poller.
     *
     * @param numOfPlayers the number of players the hostPlayer sets
     * @param gameName     the name of the game that is given automatically,
     *                     but can be named differently by the host player.
     * @param hostColor    the color that the hostPlayer chooses for their
     *                     pawn in the game
     *                     This creates a thread which initializes the game and waits for
     *                     more players to join.
     */
    public void createGame(final int numOfPlayers, final String gameName, final String hostColor) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameInfo test = proxy.createGame(model.getCurrentPlayerName(), numOfPlayers, gameName, hostColor);
                System.out.println("WHAT WHAT");
                clientFacade.createGame(test);
            }
        });
        thread.start();
//        try {
//            thread.join();
//            poller.setPlayerWatingPolling();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Allows player to join a game that has not started yet.
     * Starts player waiting poller.
     *
     * @param gameID the id of the game the player desires to join.
     * @param color  the color that the current player chooses for
     *               their pawn in the game.
     *               This creates a thread that waits with the poller for more
     *               players to join the game and then starts the game
     */
    public void joinGame(final int gameID, final String color) {
       // System.out.println("the facade join game " + proxy.getGames());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientFacade.joinGame(proxy.joinGame(model.getCurrentPlayerName(), gameID, color));
            }
        });
        thread.start();
//        try {
//            thread.join();
//            poller.setPlayerWatingPolling();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    public void reJoinGame(final int gameID) {
        // System.out.println("the facade join game " + proxy.getGames());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientFacade.reJoinGame(proxy.reJoinGame(model.getCurrentPlayerName(), gameID));
            }
        });
        thread.start();
//        try {
//            thread.join();
//            poller.setPlayerWatingPolling();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Call to get the games in the Game Station
     *
     * @param player name of player who's client is trying to get the list of games in the GameLobby
     */
    public void getGames(final Player player) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                clientFacade.updateGameList(proxy.getGames(model.getCurrentPlayerName()));
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void startGame() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                proxy.startGame(model.getGameID());
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * gets the game, specifically the current game state, that the player is in
     *
     * @param player the player class of the user. Includes their username
     *               and password which get passed when getting calling the
     *               ServerProxy to get the current game state
     * @param gameID the id of the game that we are finding the state of.
     */
    public void getGame(final Player player, final int gameID) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Game game = proxy.getGame(gameID);
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    ICommand command;
                    while ((command = proxy.getNextCommand(model.getCurrentPlayerName(), model.getGameID())) != null) {
                        clientFacade.executeCommand(command);
                    }
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void drawDestinationCard() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    clientFacade.addDestinationCard(proxy.drawDestinationCard(model.getCurrentPlayerName(), model.getGameID()));
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void drawTrainCard() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    clientFacade.addTrainCard(proxy.drawTrainCard(model.getCurrentPlayerName(), model.getGameID()));
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void pickTrainCard(final int index) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    clientFacade.addTrainCard(proxy.pickTrainCard(model.getCurrentPlayerName(), model.getGameID(), index));
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void discardTrainCard(final TrainCard card) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    proxy.discardTrainCard(model.getCurrentPlayerName(), model.getGameID(), card);
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void claimRoute(final int routeId, final TrainType type) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    proxy.claimRoute(model.getCurrentPlayerName(), model.getGameID(), routeId, type);
                }
            }
        });
        thread.start();
    }

    public void finishTurn() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    proxy.finishTurn(model.getCurrentPlayerName(), model.getGameID());
                }
            }
        });
        thread.start();
    }

    public void finishGame() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    proxy.finishGame(model.getCurrentPlayerName(), model.getGameID());
                }
            }
        });
        thread.start();
    }

    public void putBackDestinationCard(final DestinationCard card) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (model.hasCurrentGame()) {
                    proxy.returnDestinationCard(model.getCurrentPlayerName(), model.getGameID(), card);
                }
            }
        });
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void sendChat(final Chat chat) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                proxy.sendChat(chat);
            }
        });
        thread.start();
    }

    public void setServerTrainCount(final int count) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                proxy.setServerTrainCount(count);
            }
        });
        thread.start();
    }

    //temporary method
//    public void updateGameStarted() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ClientModel model = ClientModel.getInstance();
//                if(model.hasCurrentGame()) {
//                    ClientGameStartedCommand command = proxy.getGameStartedCommand();
//                    if(command == null || command.getGameInfo() == null) return;
//                    List<BaseCommand> commands = new ArrayList<>();
//                    commands.add(command);
//                    ClientFacade.getInstance().executeCommands(commands);
//                }
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Sets the player as the current player
     * @param player the player that is the current player
     * @return true if the setCurrentPlayer was successful
     * or false otherwise.
     */
//    private boolean setCurrentPlayer(Player player)
//    {
//        if(player != null)
//        {
//            ClientFacade.getInstance().setCurrentPlayer(player);
//            return true;
//        }
//
//        return false;
//    }

//    private Player getCurrentPlayer(){
//        return ClientModel.getInstance().getCurrentPlayer();
//    }

//    private int getCurrentGame(){
//        return ClientModel.getInstance().getGameID();
//    }

    /**
     * sets the private variable 'set' to either true or false
     * @param switchTo the boolean to set the variable 'set'
     */
//    private void setSet(boolean switchTo)
//    {
//        this.set = switchTo;
//    }

//    private void sendTrainCardChoice(TrainCard trainCard){
//
//    }
//    private void claimRoute(Route route){
//        proxy.claimRoute(this.getCurrentPlayer().getName(),this.getCurrentGame(),route);
//    }
//    private void putDestinationCardBack(DestinationCard dCard){
//        proxy.putDestinationCardBack(dCard);
//
//    }
//    private void assignDestinationCard(DestinationCard dcard){
//
//    }
//    private void endPlayerTurn(){
//
//    }

}