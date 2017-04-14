package com.example.alec.phase_05.Server.Database;

import com.example.alec.phase_05.Shared.model.PlayerCredentials;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 4/12/2017.
 */
public class Blob_GeneratorTest {
    @Test
    public void serialize() throws Exception {
        PlayerCredentials pc = new PlayerCredentials("atangren", "myPassword");

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("FileDatabase.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pc);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void deserialize() throws Exception {
        String expected = "atangren";
        String actual;

        PlayerCredentials pc = new PlayerCredentials("atangren", "myPassword");
        Blob_Generator.andrewSerialize(pc);
        //String blob = Blob_Generator.serialize(pc);
        PlayerCredentials restored = (PlayerCredentials) Blob_Generator.andrewDeserialize();
        actual = restored.getUsername();

        assertEquals(expected, actual);
    }

}