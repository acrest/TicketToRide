package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by clarkpathakis on 2/4/17.
 */

public class Poller {

    private static int DEFAULT_POLL_INTERVAL = 200;
    private IServer server;
    private Timer poller;
    private int state = -1;

    private static Poller instance;
    private GameDescription game;



    /**
     * Construct a poller instance using the given server
     * @param theServer An instance of IServer - could be a real networking server,
     *               or a mock server for testing.
     */
    public Poller(IServer theServer) {
        server = theServer;
    }

    public static Poller getInstance() {
        if(instance == null) {
            instance = new Poller(ServerProxy.getInstance());
        }
        return instance;
    }

    public boolean isRunning() {

        return poller != null;
    }

    public void start() {
        assert server != null;
        //checks if poller is null
        if (poller != null) {
            return;
        }

        poller = new Timer(true);
        poller.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Player currentPlayer = ClientModel.getInstance().getCurrentPlayer();
                switch (state) {
                    case 1:

                        GameState currentGame = server.getGame(currentPlayer.getName(), currentPlayer.getPassword(),
                                ClientModel.getInstance().getCurrentGame().getGameDescription().getID());
                        //server.getCurrentModel(Facade.getInstance().getGame().getVersion());

                        break;
                    case 2:

                        server.getLatestPlayers(currentPlayer.getName(), currentPlayer.getPassword());
                        break;
                    case 3:


                        List<GameDescription> games = server.getGames(currentPlayer.getName(), currentPlayer.getPassword());
                }
            }
        }, 0, DEFAULT_POLL_INTERVAL);
    }


    public void stop() {
        poller.cancel();
        poller = null;
    }

    public void setModelPolling() {
        state = 1;
    }

    public void setPlayerWatingPolling() {
        state = 2;
    }

    public void setListGamePolling() {
        state = 3;
    }






}