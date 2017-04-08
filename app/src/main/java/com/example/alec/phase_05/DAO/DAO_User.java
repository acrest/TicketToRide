package com.example.alec.phase_05.DAO;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/8/2017.
 */

public class DAO_User {
    private SQLiteDatabase db;

    private static DAO_User ourInstance = new DAO_User();

    /**
     * @return returns an instance of daoUser.
     */
    public static DAO_User getInstance() {
        return ourInstance;
    }

    private DAO_User() {
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

    /**
     * Drops our User table within the database.
     */
    public void dropTable()
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

    /** Add an user into the database.
     * @param username the user to be added to the database.
     */
    public void addUser(String username, String password)
    {
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

//    public void addUsersFromUser(ArrayList<User> users){
//        Connection c = null;
//
//        try {
//            c = DriverManager.getConnection("jdbc:sqlite:FamilyMap.sqlite");
//
//            for(int i = 0; i < users.size(); i++){
//                User user = users.get(i);
//
//                PreparedStatement prep = c.prepareStatement( "insert into USER values (?, ?, ?, ?, ?, ?, ?);");
//                prep.setString(1, user.getUsername());
//                prep.setString(2, user.getPassword());
//                prep.setString(3, user.getEmail());
//                prep.setString(4, user.getFirstname());
//                prep.setString(5, user.getLastname());
//                prep.setString(6, user.getGender());
//                prep.setString(7, user.getPersonID());
//                prep.addBatch();
//
//                c.setAutoCommit(false);
//                prep.executeBatch();
//                c.setAutoCommit(true);
//            }
//
//            c.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                c.close();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }

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

    /** Returns all the data for the users inside the database.
     * @return users stored within database.
     */
    /*public User[] getUser(){return null;}

    public  ArrayList<User> getUsers(Connection c)
    {
        //Cursor cursor = db.rawQuery("select * from USER", EMPTY_ARRAY_OF_STRINGS);
        ArrayList<User> users = new ArrayList<>();

        try {
            PreparedStatement stmt = c.prepareStatement("Select * from USER");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                String gender = rs.getString(6);
                String personId = rs.getString(7);

                User user = new User(userName, password, email, firstName, lastName, gender, personId);
                users.add(user);
            }

            stmt.close();
        } catch (SQLException e) {

        }

        return users;
    }*/
}
