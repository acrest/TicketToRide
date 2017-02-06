package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.*;
import java.net.*;
import java.io.*;

/**
 * Handles client requests to the server.
 * Created by samuel on 2/4/17.
 */
public class ClientCommunicator
{
    private static ClientCommunicator instance;

    /**
     * @return singleton instance of this class
     */
    public static ClientCommunicator getInstance()
    {
        if(instance == null) instance = new ClientCommunicator();
        return instance;
    }

    private String serverIP;
    private String serverPort;

    /**
     * Constructs a ClientCommuncator with a null server port and ip.
     * This method should only be called by the getInstance() method.
     */
    public ClientCommunicator()
    {
        serverIP = null;
        serverPort = null;
    }

    /**
     * @param s server ip
     */
    public void setServerIP(String s)
    {
        serverIP = s;
    }

    /**
     * @param s server port
     */
    public void setServerPort(String s)
    {
        serverPort = s;
    }

    /**
     * executes cmd on the server and returns the Result of cmd
     * @param cmd command to be sent to and executed by the server
     * @return Result of cmd.execute() on the server
     */
    public ClientResult executeCommandOnServer(BaseCommand cmd)
    {
        String serializedCommand = SerDes.serialize(cmd);
        String responseBody = sendAndGetResponse(serializedCommand, "/command");
        Result result = SerDes.deserializeResult(responseBody);
        return (ClientResult) result;
    }

    /**
     * creates a POST request and sends the given data to the given handler
     * @param requestBody data to send to the server
     * @param handler handler to connect to on the server
     * @return data returned by the server
     */
    public String sendAndGetResponse(String requestBody, String handler)
    {
        try
        {
            URL url = new URL("http://" + serverIP + ":" + serverPort + handler);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);

            http.connect();

            OutputStream reqBody = http.getOutputStream();
            writeString(requestBody, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream respBody = http.getInputStream();
                return readString(respBody);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0)
        {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}