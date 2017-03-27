package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class DrawDestinationState implements GameState {
    private ClientGame state = null;
    private Facade facade;


    public DrawDestinationState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck(String player) throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void pickTrainCard(String player, int cardIndex) throws StateWarning {
        throw new StateWarning("You drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void drawDestinationCard(String player) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void putBackDestinationCard(String player, DestinationCard card) throws StateWarning {
        System.out.println("Putting back destination card(s).");
        facade.putBackDestinationCard(card);
        state.setTurnState(new ReturnDestinationState(state));
    }

    @Override
    public void claimRoute(String player, int routeId) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }

    @Override
    public void endTurn(String player) throws StateWarning {
        throw new StateWarning("You already drew a destination card. Pick if you want to return some.");
    }
}
