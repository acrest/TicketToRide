package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.FileUtility;
import com.example.alec.phase_05.Server.Database.database_interface.PlayerDAO;
import com.example.alec.phase_05.Shared.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/9/2017.
 */
public class FilePlayerDAO implements PlayerDAO {

    @Override
    public void setUp(){

    }

    public FilePlayerDAO() {

    }

    private File getPlayerDirectory() {
        File playerDir = new File("players");
        if (!playerDir.exists()) {
            playerDir.mkdir();
        }
        return playerDir;
    }

    @Override
    public void addUser(String username, String password) {
        try {
            FileWriter out = new FileWriter(new File(getPlayerDirectory(), username));
            out.write(password);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(String username) {
        File playerFile = new File(getPlayerDirectory(), username);
        playerFile.delete();
    }

    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (File playerFile : getPlayerDirectory().listFiles()) {
            try {
                FileReader in = new FileReader(playerFile);
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[128];
                int read;
                while ((read = in.read(buffer)) >= 0) {
                    builder.append(buffer, 0, read);
                }
                users.add(new User(playerFile.getName(), builder.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void clear() {
        FileUtility.recursiveDelete(getPlayerDirectory());
    }

//    public void loadUsers(){  //Load Users from database to model.
//
//    }
}
