package com.example.alec.phase_05.Client.Model;

import com.example.alec.phase_05.Client.Facade;
import com.example.alec.phase_05.Shared.model.GameMap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by clarkpathakis on 3/22/17.
 */
public class ClientGameTest {
    @Test
    public void drawCard() throws Exception {
        Facade facade = Facade.getInstance();
        facade.createGame(2,"name", "red");

        IClientBank bank;
        GameMap gameMap;
        //ClientGame testGame = new ClientGame(1, "Name", 2, bank, gameMap);
    }

    @Test
    public void pickCard() throws Exception {

    }

    @Test
    public void endTurn() throws Exception {

    }

    @Test
    public void pickTrainCard() throws Exception {

    }

    @Test
    public void claimRoute() throws Exception {

    }

    @Test
    public void endTurn1() throws Exception {

    }

}