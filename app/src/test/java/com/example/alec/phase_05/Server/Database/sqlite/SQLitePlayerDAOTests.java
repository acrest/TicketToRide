package com.example.alec.phase_05.Server.Database.sqlite;

import com.example.alec.phase_05.Server.Database.Database;
import com.example.alec.phase_05.Shared.model.User;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by samuel on 4/13/17.
 */

public class SQLitePlayerDAOTests {
    @BeforeClass
    public static void setUp() {
        Database.init("file");
    }

    @AfterClass
    public static void tearDown() {
        Database.getPlayerDAO().clear();
    }

    @Before
    public void clearDataBase() {
        Database.getPlayerDAO().clear();
    }

    @Test
    public void testAddUser() {
        Database.getPlayerDAO().addUser("a", "test1");

        assertTrue(new File("players/a").exists());
    }

    @Test
    public void testRemoveUser() {
        Database.getPlayerDAO().addUser("b", "test2");
        Database.getPlayerDAO().removeUser("b");

        assertFalse(new File("players/b").exists());
    }

    @Test
    public void testGetUsers() {
        Database.getPlayerDAO().addUser("a", "test1");
        Database.getPlayerDAO().addUser("b", "test2");
        Database.getPlayerDAO().addUser("c", "test3");
        Database.getPlayerDAO().addUser("d", "test4");

        List<User> users = Database.getPlayerDAO().getUsers();
        assertEquals(4, users.size());
        for (User user : users) {
            switch (user.getUsername()) {
                case "a":
                    assertEquals("test1", user.getPassword());
                    break;
                case "b":
                    assertEquals("test2", user.getPassword());
                    break;
                case "c":
                    assertEquals("test3", user.getPassword());
                    break;
                case "d":
                    assertEquals("test4", user.getPassword());
                    break;
                default:
                    fail();
            }
        }
    }
}
