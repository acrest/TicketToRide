package com.example.alec.phase_05.Client.Presenter;

import android.util.Log;

import com.example.alec.phase_05.Shared.model.GameDescription;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

/**
 * Created by samuel on 2/11/17.
 */

public class MockPresenterGameStation implements IPresenterGameStation {
    private static String[] colors;

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
    public void update(Observable observable, Object o) {
        listener.updateGameList(generateRandomDescriptions());
    }

    private List<GameDescription> generateRandomDescriptions() {
        List<GameDescription> gameDescriptions = new ArrayList<>();
        int count = new Random().nextInt(10);
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
        Map<Player, String> playerColors = generateRandomPlayerColors(players);
        return new GameDescription(id, name, max, players, playerColors);
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
        List<Player> players = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(4) + 2;
        for(int i = 0; i < size; ++i) {
            players.add(generateRandomPlayer());
        }
        return players;
    }

    private Player generateRandomPlayer() {
        return new Player(generateRandomAlphabeticString(), generateRandomAlphabeticString());
    }

    private Map<Player, String> generateRandomPlayerColors(List<Player> players) {
        Map<Player, String> playerColors = new HashMap<>();
        for(Player player : players) {
            playerColors.put(player, generateRandomColor());
        }
        return playerColors;
    }

    public String generateRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }
}
