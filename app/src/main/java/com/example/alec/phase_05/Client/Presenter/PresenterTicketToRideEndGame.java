package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.Iterator;
import java.util.Map;

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
                while (nextIndex <= model.getGameMaxPlayers()) {
                    if (nextIndex == model.getGameMaxPlayers() || model.getPlayer(nextIndex) != null) {
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
        return model.getPlayerPoints(playerName);
    }

    @Override
    public int getAdditions(String playerName) {
        return model.getBonusPoints(playerName);
    }

    @Override
    public int getTotal(String playerName) {
        return getPoints(playerName) + getAdditions(playerName);
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
        while (playerNames.hasNext()) {
            String player = playerNames.next();
            if (maxPlayer == null || getTotal(player) > maxPoints) {
                maxPoints = getTotal(player);
                maxPlayer = player;
            }
        }
        if (maxPlayer == null) {
            return "There is no winner";
        }
        return maxPlayer + " Has Won";
    }

    @Override
    public String getLongestRouteHolder() {
        Map<IPlayer, Integer> longest = model.getLongestRoute();
        IPlayer maxPlayer = null;
        int maxLength = -1;
        for (int i = 0; i < model.getGameMaxPlayers(); i++) {
            IPlayer player = model.getPlayer(i);
            if (player != null) {
                if (maxPlayer == null || longest.get(player) > maxLength) {
                    maxPlayer = player;
                    maxLength = longest.get(player);
                }
            }
        }
        if (maxPlayer == null) {
            throw new IllegalStateException("end game reached, but there is no longest route holder");
        }
        return maxPlayer.getName();
    }

    @Override
    public void update(UpdateIndicator updateIndicator) {
        if(updateIndicator.needUpdate(ClientModel.BONUS_POINTS)) {
            Iterator<String> players = getPlayerNames();
            while(players.hasNext()) {
                String player = players.next();
                listener.updatePlayer(player);
            }
        }
    }
}
