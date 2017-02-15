package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
import com.example.alec.phase_05.Shared.command.GameDescriptionHolder;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;

/**
 * Created by clarkpathakis on 2/6/17.
 */

public class Facade {

    private static Facade _instance;
    private ServerProxy proxy = null;
    boolean set = false;
    boolean pollerStarted = false;
    Poller poller = Poller.getInstance();


    public Facade() {
        proxy = new ServerProxy(null, null);
    }

    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

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

    public void getGame(final Player player, final int gameID) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameState game = proxy.getGame(player.getName(), player.getPassword(), gameID);
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

    public void updateGameChanges() {
        ClientModel cl = ClientModel.getInstance();
        GameState game = cl.getCurrentGame();
        if(game != null) {
            //TODO: implement this
            //proxy.getGameCommands(cl.getCurrentPlayer(), game.getID(), cl.getLastUpdate());
        }
    }

    private boolean setCurrentPlayer(Player player)
    {
        if(player != null)
        {
            ClientModel.getInstance().setCurrentPlayer(player);
            return true;
        }

        return false;
    }

    private void setSet(boolean switchTo)
    {
        this.set = switchTo;
    }
}
