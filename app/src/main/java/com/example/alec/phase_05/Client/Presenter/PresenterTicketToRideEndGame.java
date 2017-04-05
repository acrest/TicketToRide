package com.example.alec.phase_05.Client.Presenter;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Client.Poller;
import com.example.alec.phase_05.Shared.model.IPlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        return getPoints(playerName) + getAdditions(playerName) + (getLongestRouteHolders().contains(playerName) ? 10 : 0);
    }

    @Override
    public String getColor(String playerName) {
        return model.getPlayerByName(playerName).getColor();
    }

    // Get the names of the winners.
    @Override
    public List<String> getWinners() {
        Iterator<String> playerNames = getPlayerNames();
        List<String> winners = new ArrayList<>();
        int maxPoints = getHighestPointCount();
        while (playerNames.hasNext()) {
            String name = playerNames.next();
            int playerTotal = getTotal(name);
            if (playerTotal == maxPoints) {
                winners.add(name);
            }
        }

        // Handle ties by taking out winners that don't have the longest route.
        if (winners.size() > 1) {
            List<String> longestRouteHolders = getLongestRouteHolders();
            Iterator<String> it = winners.iterator();
            while(it.hasNext()) {
                if (!longestRouteHolders.contains(it.next())) {
                    it.remove();
                }
            }
        }
        return winners;
    }

    private int getHighestPointCount() {
        Iterator<String> playerNames = getPlayerNames();
        int maxPoints = Integer.MIN_VALUE;
        while(playerNames.hasNext()) {
            int playerTotal = getTotal(playerNames.next());
            if(maxPoints == -1 || playerTotal > maxPoints) {
                maxPoints = playerTotal;
            }
        }
        return maxPoints;
    }

    // Get name of holder of longest route.
    @Override
    public List<String> getLongestRouteHolders() {
        Map<String, Integer> longest = model.getLongestRoute();
        List<String> longestRouteHolders = new ArrayList<>();
        int maxLength = getLongestRouteLength();
        for (int i = 0; i < model.getGameMaxPlayers(); i++) {
            IPlayer player = model.getPlayer(i);
            if (player != null) {
                Integer length = longest.get(player.getName());
                if (length != null && length == maxLength) {
                    longestRouteHolders.add(player.getName());
                }
            }
        }
        return longestRouteHolders;
    }

    private int getLongestRouteLength() {
//        if (model.getLongestRoute().size() == 0) {
//            return 21000;
//        }
        Map<String, Integer> longest = model.getLongestRoute();

        System.out.println("the map has " + longest.size());
        int maxLength = -1;
        for (int i = 0; i < model.getGameMaxPlayers(); i++) {
            IPlayer player = model.getPlayer(i);
            if (player != null) {
                Integer length = longest.get(player.getName());
                if ((length != null) && (maxLength == -1 || length > maxLength)) {
                    maxLength = length;
                }
            }
        }
        if (maxLength == -1) {
            throw new IllegalStateException("End game reached, but no player has longest route.");
        }

        return maxLength;
    }

    // Called when "Return To Menu" button is pressed on the view.
    @Override
    public void onReturnButtonPressed() {
        listener.returnToGameList();
        Poller.getInstance().setListGamePolling();
    }

    // Watches for updates in the model which change the player stats.
    // Commented out because it could be problematic without doing a full recalculation.
    @Override
    public void update(UpdateIndicator updateIndicator) {
//        if(updateIndicator.needUpdate(ClientModel.BONUS_POINTS)) {
//            Iterator<String> players = getPlayerNames();
//            while(players.hasNext()) {
//                String player = players.next();
//                listener.updatePlayer(player);
//            }
//        }
    }
}
