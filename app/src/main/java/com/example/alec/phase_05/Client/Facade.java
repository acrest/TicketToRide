package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCreateGameCommand;
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
    private boolean set = false;
    private Poller poller = null;


    public Facade() {
        proxy = new ServerProxy(null, null);
        poller = Poller.getInstance();
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
                    setSet(true);
                }
            }
        });
        thread.start();
        try {
            thread.join();
            poller.setListGamePolling();

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
                    setSet(true);
                }
            }
        });
        thread.start();
        try {
            thread.join();
            poller.setListGamePolling();

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                GameDescription newGame = proxy.createGame(player, numOfPlayers, gameName, hostColor);
            }
        }).start();
        poller.getInstance();
        poller.setPlayerWatingPolling();
    }

    public void joinGame(final int gameID, final String color) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Player player = ClientModel.getInstance().getCurrentPlayer();
                String joinedGame = proxy.joinGame(player, gameID, color);
            }
        }).start();
        poller.getInstance();
        poller.setPlayerWatingPolling();
    }

    public void getGames(final Player player) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<GameDescription> gameList = proxy.getGames(player.getName(), player.getPassword());
                ClientFacade.getInstance().updateGameList(gameList);
            }
        }).start();
    }

    public void getGame(final Player player, final int gameID) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameState game = proxy.getGame(player.getName(), player.getPassword(), gameID);
            }
        }).start();
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
