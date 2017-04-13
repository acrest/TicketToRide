package com.example.alec.phase_05.Server.Database;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by samuel on 4/12/17.
 */

public class FileGameDAOTests {
    @BeforeClass
    public void setUp() {
        Database.init("file");
    }

    @AfterClass
    public void tearDown() {
        Database.getGameDAO().
    }

    @Test
    public void testSaveGame() {

    }
}
