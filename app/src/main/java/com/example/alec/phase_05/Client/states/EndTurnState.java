package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class EndTurnState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public EndTurnState(ClientGame playerTurnStates) {
        state = playerTurnStates;
        facade = Facade.getInstance();
    }

    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void drawDestinationCards() throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void putBackDestinationCards(DestinationCard[] cards) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void claimRoute(int routeId, TrainType type) throws StateWarning {
        throw new StateWarning("Turn ended. You need to wait until it is your turn again.");
    }

    @Override
    public void endTurn() throws StateWarning {
        throw new StateWarning("Can't end turn when it's not your turn.");
    }

    @Override
    public String toString() {
        return ClientModel.getInstance().getGame().getCurrentPlayerTurn() + "'s Turn";
    }
}
