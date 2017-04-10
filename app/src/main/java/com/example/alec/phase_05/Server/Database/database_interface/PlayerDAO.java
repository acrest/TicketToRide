package com.example.alec.phase_05.Server.Database.database_interface;

import com.example.alec.phase_05.Shared.model.User;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/9/2017.
 */

public interface PlayerDAO {

    void addUser(String username, String password);

    void removeUser(String username);

    public ArrayList<User> getUsers(Connection c);

    void loadUsers();  //Load Users from database to model.
}
