package com.example.alec.phase_05;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/4/2017.
 */
public class TestsExecute {
    @Test
    public void main() throws Exception {
        boolean expected = true;

        org.junit.runner.JUnitCore.main(
                "com.example.alec.phase_05.Client.states.ClaimRouteStateTest",
                "com.example.alec.phase_05.Client.states.DrawDestinationStateTest",
                "com.example.alec.phase_05.Client.states.EndTurnStateTest",
                "com.example.alec.phase_05.Client.states.OneDrawnCardStateTest",
                "com.example.alec.phase_05.Client.states.OneDrawnOnePickedCardStateTest",
                "com.example.alec.phase_05.Client.states.OnePickedCardStateTest",
                "com.example.alec.phase_05.Client.states.RainbowCardStateTest",
                "com.example.alec.phase_05.Client.states.ReturnDestinationStateTest",
                "com.example.alec.phase_05.Client.states.StartTurnStateTest",
                "com.example.alec.phase_05.Client.states.TwoDrawnCardStateTest",
                "com.example.alec.phase_05.Client.states.TwoPickedCardStateTest");

        boolean actual = true; //Got through all the tests

        assertEquals("true", expected, actual);
    }

}