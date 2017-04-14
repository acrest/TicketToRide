package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.states.DrawDestinationState;
import com.example.alec.phase_05.Client.states.EndTurnState;
import com.example.alec.phase_05.Client.states.OneDrawnCardState;
import com.example.alec.phase_05.Client.states.StartTurnState;
import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.OtherPlayer;
import com.example.alec.phase_05.Shared.model.Player;
import com.example.alec.phase_05.Shared.model.PlayerTurnStatus;
import com.example.alec.phase_05.Shared.model.TrainCard;

/**
 * Created by samuel on 3/2/17.
 */

public final class ClientGameFactory {

    public static IClientGame createGame(GameInfo gameInfo) {
        boolean isPlayersTurn = false;
        System.out.println("inside client game factory");
        IClientBank bank = createBank();
        TrainCard[] cards = gameInfo.getVisibleTrainCards();
        for (int i = 0; i < cards.length; ++i) {
            bank.setVisibleCard(i, cards[i]);
        }
        bank.setNumberOfTrainCards(gameInfo.getTrainCardsRemaining());
        bank.setNumberOfDestinationCards(gameInfo.getDestinationCardsRemaining());
        IClientGame game = new ClientGame(gameInfo.getId(), gameInfo.getName(), gameInfo.getMaxPlayers(),
                bank, gameInfo.getMap());

        int playerTurnIndex = gameInfo.getPlayerTurnIndex();
        PlayerTurnStatus playerStatus = gameInfo.getPlayerTurnStatus();

        Player[] players = gameInfo.getPlayers();
        for (int i = 0; i < players.length; ++i) {
            Player player = players[i];
            if (player != null) {
                if (player.getName().equals(ClientModel.getInstance().getCurrentPlayerName())) {
                    game.setPlayer(i, player);

                    // Check to see if it the current's player turn.
                    if(i == playerTurnIndex) {
                        if(playerStatus == PlayerTurnStatus.DESTINATION) {
                            //get destination cards and call for destination modle to pop up
                            game.setTurnState(new DrawDestinationState((ClientGame) game));
                        } else if (playerStatus == PlayerTurnStatus.TRAIN) {
                            //set client state to one card picked.
                            // tell user to pick one more train card.
                            game.setTurnState(new OneDrawnCardState((ClientGame) game));
                        } else {
                            //set client state to start turn state.
                            game.setTurnState(new StartTurnState((ClientGame) game));
                        }
                    }

                } else {
                    game.setPlayer(i, new OtherPlayer(players[i]));

                    // See if it is this player's turn.
                    if(playerTurnIndex == i) {
                        game.setTurnState(new EndTurnState((ClientGame) game));
                    }
                }

                // Set the current player name if it is this player's turn.
                if (playerTurnIndex == i) {
                    game.setCurrentPlayerTurn(players[i].getName());
                }
                //game.getPlayer(i).setTrainCount(Facade.getInstance().getTrainCount());
            }
        }
        for (Player player : players) {
            if (player != null) {
                game.setCurrentPlayerTurn(player.getName());
                break;
            }
        }

        return game;
    }

    private static IClientBank createBank() {
        return new ClientBank();
    }

    private void ClientGameFactory() {
    }


}
