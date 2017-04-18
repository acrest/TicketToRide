package com.example.alec.phase_05.Server.Database.sqlite;

import android.database.sqlite.SQLiteStatement;

import com.example.alec.phase_05.Server.Database.Blob_Generator;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.GameCommand;
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
            createTableCommand(c);
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
    public void clearGame(int gameId) {    //????????????????????????????????????????
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stm = c.createStatement();
            stm.executeUpdate("DELETE FROM GAME WHERE id=\'" + gameId + "\'");

            c.commit();
            c.close();
        } catch (SQLException e) {
            //c.close();
        }
    }

    @Override
    public void clearAllGames() {
        dropTableGame();
    }

    @Override
    public void addCommand(int gameId, GameCommand command) {
//        Connection c = null;
//        PreparedStatement stmt = null;

//        try {
//            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
//            c.setAutoCommit(false);
//
//            PreparedStatement prep = c.prepareStatement( "insert into COMMAND values (?, ?, ?);");
//            prep.setInt(1, gameId);
//            prep.setInt(2, 2);
//            prep.setBytes(3, Blob_Generator.serialize(command));
//            prep.addBatch();

            //c.setAutoCommit(false);
//            prep.executeBatch();
            //c.setAutoCommit(true);

//            stmt = c.prepareStatement("Select * from GAME");
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                int id = rs.getInt(1);
//
//                if(id == gameId){
//                    byte[] gameBytes = rs.getBytes(2);
//                    IServerGame game = (IServerGame) Blob_Generator.deserialize(gameBytes);
//
//                    game.addCommand(command);
//
//                    Statement stm = c.createStatement();
//                    stm.executeUpdate("DELETE FROM GAME WHERE id=\'" + gameId + "\'");
//                    c.commit();
//                    c.close();
//
//                    saveGame(game);
//                }
//            }

//            stmt.close();
//            c.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //c.close();
//        }

        Connection c = null;
        byte[] commandBlobBytes = Blob_Generator.serialize(command);

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");

            PreparedStatement prep = c.prepareStatement( "insert into COMMAND values (?, ?, ?);");
            prep.setInt(1, gameId);
            prep.setInt(2, getNumberOfCommands(gameId));
            prep.setBytes(3, commandBlobBytes);
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
    public ICommand getCommand(int gameId, int index) {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            stmt = c.prepareStatement("Select * from COMMAND WHERE gameId=" + gameId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt(2);

                if(id == index){
                    byte[] gameBytes = rs.getBytes(3);
                    ICommand command = (ICommand) Blob_Generator.deserialize(gameBytes);
                    c.close();
                    return command;
                }
            }
//            while(rs.next()){
//                int id = rs.getInt(1);
//
//                if(id == gameId){
//                    byte[] gameBytes = rs.getBytes(2);
//                    IServerGame game = (IServerGame) Blob_Generator.deserialize(gameBytes);
//                    c.close();
//                    return game.getCommand(index);
//                }
//            }

            stmt.close();
            c.close();
        } catch (SQLException e) {
            //c.close();
        }

        return null;
    }

    @Override
    public int getNumberOfCommands(int gameId) {
//        IServerGame game = getGame(gameId);
//
//        if(game != null){
//            return game.getCommandSize();
//        }

        Connection c = null;
        PreparedStatement stmt = null;
        int numCommands = 0;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            stmt = c.prepareStatement("Select * from COMMAND WHERE gameId=" + gameId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                numCommands++;
            }
//            while(rs.next()){
//                int id = rs.getInt(1);
//
//                if(id == gameId){
//                    byte[] gameBytes = rs.getBytes(2);
//                    IServerGame game = (IServerGame) Blob_Generator.deserialize(gameBytes);
//                    c.close();
//                    return game.getCommand(index);
//                }
//            }

            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //c.close();
        }

        return numCommands;
    }

    @Override
    public void clearCommands(int gameId) {
        Connection c = null;
        PreparedStatement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);

            Statement stm = c.createStatement();
            stm.executeUpdate("DELETE FROM COMMAND WHERE gameId=\'" + gameId + "\'");

            c.commit();
            c.close();

//            stmt = c.prepareStatement("Select * from GAME");
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()){
//                int id = rs.getInt(1);
//
//                if(id == gameId){
//                    byte[] gameBytes = rs.getBytes(2);
//                    IServerGame game = (IServerGame) Blob_Generator.deserialize(gameBytes);
//
//                    game.clearCommands();
//
//                    Statement stm = c.createStatement();
//                    stm.executeUpdate("DELETE FROM GAME WHERE id=\'" + gameId + "\'");
//                    c.commit();
//                    c.close();
//
//                    saveGame(game);
//                }
//            }
//
//            stmt.close();
//            c.close();
        } catch (SQLException e) {
            //c.close();
        }
    }

    @Override
    public void clearAll() {
        dropTableGame();
        dropTableCommand();
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

    public static void createTableCommand(Connection c)
    {
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMMAND" +
                    "(  gameId    INTEGER    NOT NULL," +
                    "commandId INTEGER NOT NULL," +
                    "   command    BLOB    NOT NULL)";
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

    private void dropTableCommand()
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            Statement stat = c.createStatement();
            stat.executeUpdate("drop table if exists COMMAND;");
            stat.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
