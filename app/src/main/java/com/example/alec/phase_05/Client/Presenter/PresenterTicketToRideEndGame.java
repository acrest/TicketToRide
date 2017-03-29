package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 * Created by samuel on 3/22/17.
 */

public class PresenterTicketToRideEndGame extends Presenter implements IPresenterTicketToRideEndGame {
    private ITicketToRideEndGameListener listener;
    private ClientModel model;

    public PresenterTicketToRideEndGame(ITicketToRideEndGameListener listener) {
        this.listener = listener;
        model = ClientModel.getInstance();
    }

    @Override
    public Iterator<String> getPlayerNames() {
        return new Iterator<String>() {
            private ClientModel model = ClientModel.getInstance();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return model.getPlayer(index) != null;
            }

            @Override
            public String next() {
                String player = model.getPlayer(index).getName();
                int nextIndex = index + 1;
                while(nextIndex <= model.getGameMaxPlayers()) {
                    if(nextIndex == model.getGameMaxPlayers() || model.getPlayer(nextIndex) != null) {
                        index = nextIndex;
                        break;
                    }
                }
                return player;
            }
        };
    }

    @Override
    public int getPoints(String playerName) {
        int points = model.getPlayerPoints(playerName);
        GameMap map = model.getMap(); //TODO left off here (about to change xml to "additions" rather than "penalties"
        return points;
    }

    @Override
    public int getPenalties(String playerName) {
        return 0;
    }

    @Override
    public int getTotal(String playerName) {
        return getPoints(playerName) - getPenalties(playerName);
    }

    @Override
    public String getColor(String playerName) {
        return model.getPlayerByName(playerName).getColor();
    }

    @Override
    public String getWinner() {
        //TODO handle ties
        Iterator<String> playerNames = getPlayerNames();
        String maxPlayer = null;
        int maxPoints = Integer.MIN_VALUE;
        while(playerNames.hasNext()) {
            String player = playerNames.next();
            if(maxPlayer == null || getTotal(player) > maxPoints) {
                maxPoints = getTotal(player);
                maxPlayer = player;
            }
        }
        if(maxPlayer == null) {
            return "There is no winner";
        }
        return maxPlayer;
    }

    @Override
    public String getLongestRouteHolder() {
        Map<IPlayer, Integer> longest = model.getLongestRoute();
        IPlayer maxPlayer = null;
        int maxLength = -1;
        for(int i = 0; i < model.getGameMaxPlayers(); i++) {
            IPlayer player = model.getPlayer(i);
            if(player != null) {
                if(maxPlayer == null || longest.get(player) > maxLength) {
                    maxPlayer = player;
                    maxLength = longest.get(player);
                }
            }
        }
        if(maxPlayer == null) {
            throw new IllegalStateException("end game reached, but there is no longest route holder");
        }
        return maxPlayer.getName();
    }

    @Override
    public void update(UpdateIndicator updateIndicator) {

    }
}
