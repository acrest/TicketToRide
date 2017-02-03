package com.example.alec.phase_05.Client;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Alec on 1/21/17.
 */
public class Client {

    public static void main(String[] args) {
        ClientCommunicator cc = new ClientCommunicator();

        while(true) {
            String str = null;
            String command = null;
            System.out.print("What is the string: \n");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print("Your String is ");
            System.out.print(str);
            System.out.print("\n Enter L for lower case\n Enter T to trim\n Enter P to parse\n ");

            try {
                command = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //ClientProcessorNotProxy fakeProxy = new ClientProcessorNotProxy(s, command);
            //fakeProxy.execute();


            String serverHost = "localhost";
            String serverPort = "8080";

            StringProcessorProxy_NoCommands proxy = new StringProcessorProxy_NoCommands(str,command,cc,serverHost,serverPort);



//        getGameList(serverHost, serverPort);
//        claimRoute(serverHost, serverPort);
        }
    }
/*
    private static void getGameList(String serverHost, String serverPort) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/games/list");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);	// There is no request body

            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Accept", "application/json");

            http.connect();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();

                String respData = readString(respBody);

                System.out.println(respData);
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void claimRoute(String serverHost, String serverPort) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/routes/claim");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body

            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Content-Type", "application/json");

            http.connect();

            String reqData =
                    "{" +
                            "\"route\": \"atlanta-miami\"" +
                            "}";

            OutputStream reqBody = http.getOutputStream();
            writeString(reqData, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                System.out.println("Route successfully claimed.");
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
*/
}
