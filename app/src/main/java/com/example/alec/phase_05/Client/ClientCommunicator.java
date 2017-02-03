package com.example.alec.phase_05.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;

import com.example.alec.phase_05.Shared.BaseCommand;
import com.example.alec.phase_05.Shared.Serializer;
import com.sun.net.httpserver.*;

/**
 * Created by Alec on 1/20/17.
 */
public class ClientCommunicator {


    private static ClientCommunicator _instance;

    ClientCommunicator() {

    }

    public static ClientCommunicator get_instance(){
        if(_instance == null){
            _instance = new ClientCommunicator();
        }
        return _instance;
    }


    public static String toCommand(String serverHost, String serverPort, BaseCommand myCMD, String ending ) {
        String myDataReturn = null;
        try {
            //URL url = new URL("http://" + serverHost + ":" + serverPort + "/games/list");
            URL url = new URL("http://" + serverHost + ":" + serverPort + ending );

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is no request body


            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Accept", "application/json");

            http.connect();


            String jsonString = encode(myCMD);

            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();

                String respData = readString(respBody);



                myDataReturn = new String(respData);

                //System.out.println(respData);
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return myDataReturn;
    }

    public static String toExecCommand(String serverHost, String serverPort, BaseCommand myCMD, String ending ) {
        String myDataReturn = null;
        try {
            //URL url = new URL("http://" + serverHost + ":" + serverPort + "/games/list");
            URL url = new URL("http://" + serverHost + ":" + serverPort + ending );

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is no request body


            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Accept", "application/json");

            http.connect();


            String jsonString = encode(myCMD);

            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();

                String respData = readString(respBody);



                myDataReturn = new String(respData);

                //System.out.println(respData);
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return myDataReturn;
    }


    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    public static String encode(BaseCommand requestInfo){
        Serializer ser = new Serializer();
        String encodedSTR = ser.serialize(requestInfo);
        return encodedSTR;
    }
}
