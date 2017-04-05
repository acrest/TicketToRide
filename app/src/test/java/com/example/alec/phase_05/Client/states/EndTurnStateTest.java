package com.example.alec.phase_05.Client.states;

import com.example.alec.phase_05.Client.Model.ClientBank;
import com.example.alec.phase_05.Client.Model.ClientGame;
import com.example.alec.phase_05.Shared.model.City;
import com.example.alec.phase_05.Shared.model.DestinationCard;
import com.example.alec.phase_05.Shared.model.GameMap;
import com.example.alec.phase_05.Shared.model.GameState;
import com.example.alec.phase_05.Shared.model.MyPoint;
import com.example.alec.phase_05.Shared.model.Route;
import com.example.alec.phase_05.Shared.model.StateWarning;
import com.example.alec.phase_05.Shared.model.TrainType;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/4/2017.
 */
public class EndTurnStateTest {
    @Test
    public void testDrawTrainCardFromDeck(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.drawTrainCardFromDeck();
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }

    @Test
    public void testPickTrainCard(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.pickTrainCard(1);
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }


    @Test
    public void testDrawDestinationCard(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.drawDestinationCard();
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }

    @Test
    public void testPutBackDestinationCard(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.putBackDestinationCard(new DestinationCard(new City("New Mexico", new MyPoint(0, 0)), new City("Salt Lake", new MyPoint(2, 3)), 2));
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }

    @Test
    public void testClaimRoute(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.claimRoute(1, TrainType.ANY);
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }

    @Test
    public void testEndTurnOnOtherTurn(){
        boolean passed = true;
        boolean test;

        GameState state = new EndTurnState(new ClientGame(9, "andrew", 5, new ClientBank(), new GameMap(new HashMap<String, City>(), new HashMap<Integer, Route>())));
        try {
            state.endTurn();
            test = false;
        } catch (StateWarning stateWarning) {
            System.out.println(stateWarning.toString());
            test = true;
        }

        assertEquals(passed, test);
    }
}