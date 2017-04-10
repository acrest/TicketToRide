package com.example.alec.phase_05.Server.Database;

import com.example.alec.phase_05.Shared.model.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/9/2017.
 */
public class DAO_File implements Database_DAO {
    private static DAO_File ourInstance = new DAO_File();

    public static DAO_File getInstance() {
        return ourInstance;
    }

    private DAO_File() {
    }

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public void removeUser(String username) {

    }

    @Override
    public ArrayList<User> getUsers(Connection c) {
        return null;
    }

    public void loadUsers(){  //Load Users from database to model.

    }
}
