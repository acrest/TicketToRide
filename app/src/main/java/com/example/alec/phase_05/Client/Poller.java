package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Client.command.ClientCommunicator;
import com.example.alec.phase_05.Shared.Game;

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




    public Poller(ClientCommunicator cc) {
        // do something here.
    }

    public static Poller getInstance() {
        if(instance == null) {
            instance = new Poller(ClientCommunicator.getInstance());
        }
        return instance;
    }

    public boolean isRunning() {
        return poller != null;
    }

    public void start() {
        //checks if poller is null
        if (poller != null) {
            return;
        }

        poller = new Timer(true);
        poller.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                switch (state) {
                    case 1:
                        //server.getCurrentModel(Facade.getInstance.getGame().getVersion());
                        break;
                    case 2:
                        //server.getLatestPlayers();
                        break;
                    case 3:
                        List<Game> games = server.getGames();
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
