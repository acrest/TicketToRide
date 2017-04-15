package com.example.alec.phase_05.Server.Database.sqlite;

import com.example.alec.phase_05.Server.Database.Database;
import com.example.alec.phase_05.Server.command.ServerCreateGameCommand;
import com.example.alec.phase_05.Server.model.CommandManager;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Server.model.ServerGame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by samuel on 4/12/17.
 */

public class SQLiteGameDAOTests {
    @BeforeClass
    public static void setUp() {
        Database.init("sqlite", true);
    }

    @AfterClass
    public static void tearDown() {
        Database.getGameDAO().clearAll();
    }

    @Test
    public void testSaveGame() {
        ServerGame game = new ServerGame(1, "hello", 3, new CommandManager(), null, null, null);
        Database.getGameDAO().saveGame(game);

        assertTrue(Database.getGameDAO().hasGame(1));
    }

    @Test
    public void testGetGame() {
        ServerGame game = new ServerGame(1, "hello", 3, new CommandManager(), null, null, null);
        Database.getGameDAO().saveGame(game);

        IServerGame retrieved = Database.getGameDAO().getGame(1);
        assertTrue(retrieved != null);
        assertEquals(retrieved.getID(), 1);
        assertEquals(retrieved.getName(), "hello");
    }

    @Test
    public void testHasGame() {
        ServerGame game = new ServerGame(1, "hello", 3, new CommandManager(), null, null, null);
        Database.getGameDAO().saveGame(game);

        assertTrue(Database.getGameDAO().hasGame(1));
        assertFalse(Database.getGameDAO().hasGame(2));
    }

    @Test
    public void testGetNumberOfCommands() {
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));
        Database.getGameDAO().addCommand(2, new ServerCreateGameCommand("hello", 3, "s", "red"));

        assertEquals(Database.getGameDAO().getNumberOfCommands(2), 6);
    }
}
