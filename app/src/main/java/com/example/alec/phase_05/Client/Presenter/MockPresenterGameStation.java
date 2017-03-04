package com.example.alec.phase_05.Client.Presenter;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by samuel on 2/11/17.
 */

public class MockPresenterGameStation extends Presenter implements IPresenterGameStation {
    private static String[] colors;
    private static final long TIMER_DELAY = 5000;

    static {
        colors = new String[5];
        colors[0] = "red";
        colors[1] = "blue";
        colors[2] = "yellow";
        colors[3] = "green";
        colors[4] = "black";
    }

    private IGameStationListener listener;

    public MockPresenterGameStation(IGameStationListener listener) {
        this.listener = listener;
        startRandomUpdates();
    }

    @Override
    public void joinGame(int gameID, String color) {
        Log.d("MockPresenter", "called joinGame with color = " + color);
        listener.joinGameSuccess(true);
    }

    @Override
    public void createGame(String hostColor, String gameName, int numberOfPlayers) {
        Log.d("MockPresenter", "called createGame with hostColor = " + hostColor
                + ", gameName = " + gameName
                + ", numberOfPlayers = " + numberOfPlayers);
        listener.createGameSuccess(true);
    }

    @Override
    public void update(UpdateIndicator updateIndicator) {
        listener.updateGameList(generateRandomDescriptions());
        //listener.updateGameList(generateRandomDescriptions(10));
        randomlyUpdateColors();
    }

    private void randomlyUpdateColors() {
        Random random = new Random();
        listener.hideRed(random.nextBoolean());
        listener.hideBlue(random.nextBoolean());
        listener.hideGreen(random.nextBoolean());
        listener.hideYellow(random.nextBoolean());
        listener.hideBlack(random.nextBoolean());
    }

    private List<GameDescription> generateRandomDescriptions() {
        List<GameDescription> gameDescriptions = new ArrayList<>();
        int count = new Random().nextInt(10);
        for(int i = 0; i < count; ++i) {
            gameDescriptions.add(generateRandomDescription());
        }
        return gameDescriptions;
    }

    private List<GameDescription> generateRandomDescriptions(int count) {
        List<GameDescription> gameDescriptions = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            gameDescriptions.add(generateRandomDescription());
        }
        return gameDescriptions;
    }

    private GameDescription generateRandomDescription() {
        Random random = new Random();
        int id = random.nextInt(1000);
        String name = generateRandomAlphabeticString();
        int max = random.nextInt(4) + 2;
        List<Player> players = generateRandomPlayers();
        return new GameDescription(id, name, max, players);
    }

    private String generateRandomAlphabeticString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int size = random.nextInt(20) + 1;
        for(int i = 0; i < size; ++i) {
            sb.append(generateRandomAlphabeticChar());
        }
        return sb.toString();
    }

    private char generateRandomAlphabeticChar() {
        Random random = new Random();
        char c = 'a';
        return (char)(c + random.nextInt(26));
    }

    private List<Player> generateRandomPlayers() {
        Random random = new Random();
        int size = random.nextInt(4) + 2;
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            players.add(generateRandomPlayer());
        }
        return players;
    }

    private Player generateRandomPlayer() {
        return new Player(generateRandomAlphabeticString(), generateRandomAlphabeticString());
    }

    private String[] generateRandomPlayerColors(Player[] players) {
        String[] playerColors = new String[players.length];
        for(int i = 0; i < playerColors.length; ++i) {
            playerColors[i] = generateRandomColor();
        }
        return null;
    }

    private String generateRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }

    private void startRandomUpdates() {
        new RandomUpdateTask().execute();
    }

    @Override
    public void update(UpdateIndicator updateIndicator) {

    }

    private class RandomUpdateTask extends AsyncTask<Void, Void, Void> {
        Runnable runnable;

        public RandomUpdateTask() {
            runnable = new Runnable() {
                @Override
                public void run() {
                    update(null, null);
                }
            };
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while(true) {
                try {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(runnable);
                    Thread.sleep(TIMER_DELAY);
                } catch(InterruptedException e) {}
            }
        }
    }
}
