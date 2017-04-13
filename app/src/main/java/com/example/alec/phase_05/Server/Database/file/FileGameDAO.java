package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.Blob_Generator;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by samuel on 4/10/17.
 */

public class FileGameDAO implements GameDAO {
    public FileGameDAO() {

    }

    @Override
    public void saveGame(IServerGame game) {
        try {
            FileOutputStream out = new FileOutputStream(new File("game" + game.getID() +  "/game"));
            out.write(Blob_Generator.serialize(game));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IServerGame getGame(int gameId) {
        try {
            FileInputStream in = new FileInputStream(new File("game" + gameId + "/game"));
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            int read;
            byte[] buffer = new byte[128];
            while((read = in.read(buffer)) >= 0) {
                data.write(buffer, 0, read);
            }
            return (IServerGame) Blob_Generator.deserialize(data.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean hasGame(int gameId) {
        File gameFile = new File("game" + gameId + "/game");
        return gameFile.exists();
    }

    @Override
    public void clearGame(int gameId) {
        File gameFile = new File("game" + gameId + "/game");
        gameFile.delete();
    }

    @Override
    public void clearAllGames() {
        File root = new File(".");
        for (String fileName : root.list()) {
            if (fileName.substring(0, 4).equals("game")) {
                try {
                    clearGame(Integer.parseInt(fileName.substring(4)));
                } catch (NumberFormatException e) {

                }
            }
        }
    }

    @Override
    public void addCommand(int gameId, ICommand command) {
        try {
            FileOutputStream out = new FileOutputStream(new File("game" + gameId +  "/command" + getNumberOfCommands(gameId)));
            out.write(Blob_Generator.serialize(command));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ICommand getCommand(int gameId, int index) {
//        try {
//            Foe;
//        }
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

    }
}
