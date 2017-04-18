package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Client.command.ClientChatSentCommand;
import com.example.alec.phase_05.Client.command.ClientClaimRouteCommand;
import com.example.alec.phase_05.Server.Database.Database;
import com.example.alec.phase_05.Server.Database.file.FileGameDAO;
import com.example.alec.phase_05.Server.command.ServerCreateGameCommand;
import com.example.alec.phase_05.Server.model.CommandManager;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Server.model.ServerGame;
import com.example.alec.phase_05.Shared.model.Chat;
import com.example.alec.phase_05.Shared.model.TrainType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by samuel on 4/12/17.
 */

public class FileGameDAOTests {
    @BeforeClass
    public static void setUp() {
        Database.init("file", true);
    }

    @AfterClass
    public static void tearDown() {
        Database.getGameDAO().clearAll();
    }

    @Test
    public void testSaveGame() {
        ServerGame game = new ServerGame(1, "hello", 3, new CommandManager(), null, null, null);
        Database.getGameDAO().saveGame(game);

        try {
            File gameFile = new File("game1/game");
            assertTrue(gameFile.exists());
        } catch(Exception e) {
            fail();
        }
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
        Database.getGameDAO().addCommand(2, new ClientClaimRouteCommand("Andrew", 1, 1, TrainType.ANY));
        Database.getGameDAO().addCommand(2, new ClientChatSentCommand(new Chat("Alex", 1, "hello", "blue")));

        assertEquals(Database.getGameDAO().getNumberOfCommands(2), 6);
    }
}
