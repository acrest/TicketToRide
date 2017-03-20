package com.example.alec.phase_05.Server.communication;

import com.example.alec.phase_05.Shared.command.*;
import com.example.alec.phase_05.Shared.communication.SerDes;
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

/**
 * Contains methods needed by handler classes.
 * Created by samuel on 2/4/17.
 */
public abstract class HandlerBase implements HttpHandler
{
    /**
     * @param t HttpExchange containing the request body
     * @return the read request body
     */
    protected String readRequestBody(HttpExchange t)
    {
        try
        {
            InputStream in = t.getRequestBody();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[128];
            int r;
            while((r = br.read(buf)) != -1)
            {
                sb.append(buf, 0, r);
            }
            br.close();
            in.close();
            return sb.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Serializes r and writes it to response body.
     * @param t HttpExchange object containing response body stream
     * @param r Result to write to response body
     */
    protected void sendResponse(HttpExchange t, Result r)
    {
        try
        {
            if(r != null)
            {
                String ser = SerDes.serialize(r);
                t.sendResponseHeaders(HttpURLConnection.HTTP_OK, ser.length());
                OutputStream resBody = t.getResponseBody();
                resBody.write(ser.getBytes("UTF-8"));
                resBody.close();
            }
            else
            {
                t.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
