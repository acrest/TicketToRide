package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;
import com.example.alec.phase_05.Shared.model.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/9/2017.
 */
public class FilePlayerDAO implements PlayerDAO {
    private static FilePlayerDAO ourInstance = new FilePlayerDAO();

    public static FilePlayerDAO getInstance() {
        return ourInstance;
    }

    private FilePlayerDAO() {
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
