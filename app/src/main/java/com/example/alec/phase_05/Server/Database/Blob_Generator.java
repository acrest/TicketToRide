package com.example.alec.phase_05.Server.Database;

import android.util.Base64;

import com.example.alec.phase_05.Shared.model.GameInfo;
import com.example.alec.phase_05.Shared.model.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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

    public static byte[] serialize(Object o){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream so = new ObjectOutputStream(bo);
            so.writeObject(o);
            so.flush();
            return bo.toByteArray();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static Object deserialize(byte[] b){
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
            return si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public static void andrewSerialize(Object o){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("FileDatabase");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in FileDatabase.txt");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public static Object andrewDeserialize(){
        try {
            FileInputStream fileIn = new FileInputStream("FileDatabase");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object o = (Object) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized data from FileDatabase.txt");
            return o;
        }catch(IOException i) {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }
    }
}
