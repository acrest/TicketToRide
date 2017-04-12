package com.example.alec.phase_05.Server.Database;

import android.util.Base64;

import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by Andrew on 4/10/2017.
 */

public class Blob_Generator {
    public static String generate_game_blob(GameInfo game){
        return null;
    }

    public static String generate_command_blob(){
        return null;
    }

    public String serialize(Object o){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(o);
            so.flush();
            return bo.toString();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Object deserialize(String s){
        try {
            byte b[] = s.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            return si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
