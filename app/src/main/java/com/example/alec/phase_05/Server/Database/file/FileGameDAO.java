package com.example.alec.phase_05.Server.Database.file;

import com.example.alec.phase_05.Server.Database.Blob_Generator;
import com.example.alec.phase_05.Server.Database.FileUtility;
import com.example.alec.phase_05.Server.Database.database_interface.GameDAO;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.ICommand;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by samuel on 4/10/17.
 */

public class FileGameDAO implements GameDAO {
    public FileGameDAO() {

    }

    private File getGameDirectory(int gameId) {
        File gameDir = new File("game" + gameId);
        if (!gameDir.exists()) {
            gameDir.mkdir();
        }
        return gameDir;
    }

    @Override
    public void saveGame(IServerGame game) {
        try {
            FileOutputStream out = new FileOutputStream(new File(getGameDirectory(game.getID()), "game"));
            out.write(Blob_Generator.serialize(game));
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IServerGame getGame(int gameId) {
        try {
            FileInputStream in = new FileInputStream(new File(getGameDirectory(gameId), "game"));
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            int read;
            byte[] buffer = new byte[128];
            while((read = in.read(buffer)) >= 0) {
                data.write(buffer, 0, read);
            }
            data.close();
            in.close();
            return (IServerGame) Blob_Generator.deserialize(data.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean hasGame(int gameId) {
        File gameFile = new File(getGameDirectory(gameId), "/game");
        return gameFile.exists();
    }

    @Override
    public void clearGame(int gameId) {
        File gameFile = new File(getGameDirectory(gameId), "game");
        gameFile.delete();
    }

    @Override
    public void clearAllGames() {
        File root = new File(".");
        for (String fileName : root.list()) {
            if (fileName.length() >= 4 && fileName.substring(0, 4).equals("game")) {
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
            FileOutputStream out = new FileOutputStream(new File(getGameDirectory(gameId), "command" + getNumberOfCommands(gameId)));
            out.write(Blob_Generator.serialize(command));
            out.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ICommand getCommand(int gameId, int index) {
        try {
            FileInputStream in = new FileInputStream(new File(getGameDirectory(gameId), "command" + index));
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            int read;
            byte[] buffer = new byte[128];
            while((read = in.read(buffer)) >= 0) {
                data.write(buffer, 0, read);
            }
            data.close();
            in.close();
            return (ICommand) Blob_Generator.deserialize(data.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getNumberOfCommands(int gameId) {
        int count = 0;
        for (String fileName : getGameDirectory(gameId).list()) {
            if (fileName.length() >= 7 && fileName.substring(0, 7).equals("command")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void clearCommands(int gameId) {
        for (File file : getGameDirectory(gameId).listFiles()) {
            if (file.getName().length() >= 7 && file.getName().substring(0, 7).equals("command")) {
                file.delete();
            }
        }
    }

    @Override
    public void clearAll() {
        for (File file : new File(".").listFiles()) {
            if (file.getName().length() >= 4 && file.getName().substring(0, 4).equals("game")) {
                FileUtility.recursiveDelete(file);
            }
        }
    }

    public void setUp(){

    }
}
