package com.example.alec.phase_05.Server.Database.sqlite;

import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;
import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/9/2017.
 */
public class SQLitePlayerDAO implements PlayerDAO {
    private static SQLitePlayerDAO ourInstance = new SQLitePlayerDAO();

    public static SQLitePlayerDAO getInstance() {
        return ourInstance;
    }

    private SQLitePlayerDAO() {
    }

    public void addUser(String username, String password){
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");

            PreparedStatement prep = c.prepareStatement( "insert into USER values (?, ?);");
            prep.setString(1, username);
            prep.setString(2, password);
            prep.addBatch();

            c.setAutoCommit(false);
            prep.executeBatch();
            c.setAutoCommit(true);

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

    public void removeUser(String username){
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            Statement stm = c.createStatement();
            stm.executeUpdate("DELETE FROM USER WHERE username=\'" + username + "\'");

            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
    }

    public ArrayList<User> getUsers(Connection c){
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement stmt = c.prepareStatement("Select * from USER");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String username = rs.getString(1);
                String password = rs.getString(2);

                User user = new User(username, password);
                users.add(user);
            }

            stmt.close();
        } catch (SQLException e) {

        }

        return users;
    }

    public void loadUsers(){  //Load Users from database to model.
        Connection c = null;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            ArrayList<User> users = getUsers(c);
            ServerFacade.getInstance().loadUsers(users);
            System.out.println("Loaded users.");
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void instantiateTables()
    {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            System.out.println("Opened database successfully");
            createTableUser(c);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void createTableUser(Connection c)
    {
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USER" +
                    "(  username    TEXT    NOT NULL," +
                    "   password    TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTableUser()
    {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:TicketToRide.sqlite");
            Statement stat = c.createStatement();
            stat.executeUpdate("drop table if exists USER;");
            stat.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
