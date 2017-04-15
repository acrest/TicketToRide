package com.example.alec.phase_05.Server.Database.sqlite;

import android.database.sqlite.SQLiteStatement;

import com.example.alec.phase_05.Server.Database.Blob_Generator;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by samuel on 4/10/17.
 */

public class SQLiteGameDAO implements GameDAO {
    public SQLiteGameDAO() {

    }

    @Override
    public void setUp(){
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            System.out.println("Opened database successfully");
            createTableGame(c);
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGame(IServerGame game) {
        /*Connection c = null;
        byte[] gameBlobBytes = Blob_Generator.serialize(game);

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");

            SQLiteStatement p = sqlite.compileStatement("insert into memes(img, name) values(?)");

            p.bindBlob(1, gameBlobBytes);
            p.execute();

            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }*/
    }

    @Override
    public IServerGame getGame(int gameId) {
        return null;
    }

    @Override
    public boolean hasGame(int gameId) {
        return false;
    }

    @Override
    public void clearGame(int gameId) {

    }

    @Override
    public void clearAllGames() {

    }

    @Override
    public void addCommand(int gameId, ICommand command) {

    }

    @Override
    public ICommand getCommand(int gameId, int index) {
        return null;
    }

    @Override
    public int getNumberOfCommands(int gameId) {
        return 0;
    }

    @Override
    public void clearCommands(int gameId) {

    }

    @Override
    public void clearAll() {
        dropTableGame();
    }

    public static void createTableGame(Connection c)
    {
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS GAME" +
                    "(  game    BLOB    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTableGame()
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            Statement stat = c.createStatement();
            stat.executeUpdate("drop table if exists GAME;");
            stat.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
