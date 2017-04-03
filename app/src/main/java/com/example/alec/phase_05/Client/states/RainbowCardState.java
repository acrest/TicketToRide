package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.Game;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainType;

/**
 * Created by clarkpathakis on 3/22/17.
 */

public class RainbowCardState implements GameState {
    private ClientGame state = null;
    private Facade facade;

    public RainbowCardState(ClientGame playTurnStates) {

        state = playTurnStates;
        facade = Facade.getInstance();
    }


    @Override
    public void drawTrainCardFromDeck() throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void pickTrainCard(int cardIndex) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void drawDestinationCard() throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void putBackDestinationCard(DestinationCard card) throws StateWarning {
        throw new StateWarning("Cannot draw any more cards. You must end your turn now.");
    }

    @Override
    public void claimRoute(int routeId) throws StateWarning {
        throw new StateWarning("Already drew card. You must end your turn now.");
    }

    @Override
    public void endTurn()throws StateWarning  {
        System.out.println("Turn ended.");
        facade.finishTurn();
        state.setTurnState(new EndTurnState(state));
    }

    @Override
    public String toString() {
        return "Rainbow Card Picked";
    }
}
