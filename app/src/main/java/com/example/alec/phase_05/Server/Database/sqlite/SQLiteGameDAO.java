package com.example.alec.phase_05.Server.Database.sqlite;

import android.database.sqlite.SQLiteStatement;

import com.example.alec.phase_05.Server.Database.Blob_Generator;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.User;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            //Class.forName("org.sqlite.JDBC");
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
        Connection c = null;
        byte[] gameBlobBytes = Blob_Generator.serialize(game);

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");

            PreparedStatement prep = c.prepareStatement( "insert into GAME values (?, ?);");
            prep.setInt(1, game.getID());
            prep.setBytes(2, gameBlobBytes);
            prep.addBatch();

            //c.setAutoCommit(false);
            prep.executeBatch();
            //c.setAutoCommit(true);

            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public IServerGame getGame(int gameId) {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            stmt = c.prepareStatement("Select * from GAME");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);

                if(id == gameId){
                    byte[] gameBytes = rs.getBytes(2);

                    c.close();
                    return (IServerGame) Blob_Generator.deserialize(gameBytes);
                }
            }

            stmt.close();
            c.close();
        } catch (SQLException e) {
            //c.close();
        }

        return null;
    }

    @Override
    public boolean hasGame(int gameId)
    {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            stmt = c.prepareStatement("Select * from GAME");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);

                if(id == gameId){
                    c.close();
                    return true;
                }
            }

            stmt.close();
            c.close();
        } catch (SQLException e) {

        }

        return false;
    }

    @Override
    public void clearGame(int gameId) {

    }

    @Override
    public void clearAllGames() {
        dropTableGame();
    }

    @Override
    public void addCommand(int gameId, ICommand command) {

    }

    @Override
    public ICommand getCommand(int gameId, int index) {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            stmt = c.prepareStatement("Select * from GAME");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);

                if(id == gameId){
                    byte[] gameBytes = rs.getBytes(2);

                    c.close();
                    IServerGame game = (IServerGame) Blob_Generator.deserialize(gameBytes);
                    return game.getCommand(index);
                }
            }

            stmt.close();
            c.close();
        } catch (SQLException e) {
            //c.close();
        }

        return null;
    }

    @Override
    public int getNumberOfCommands(int gameId) {
        IServerGame game = getGame(gameId);

        if(game != null){
            return game.getCommandSize();
        }

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
                    "(  id    INTEGER    NOT NULL," +
                    "   game    BLOB    NOT NULL)";
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
