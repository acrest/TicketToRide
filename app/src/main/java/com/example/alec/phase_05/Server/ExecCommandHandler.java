package com.example.alec.phase_05.Server;

import android.util.Base64;

import com.example.alec.phase_05.Shared.BaseCommand;
import com.example.alec.phase_05.Shared.Results;
import com.example.alec.phase_05.Shared.Serializer;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Alec on 1/20/17.
 */
public class ExecCommandHandler implements com.sun.net.httpserver.HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {

                    String authToken = reqHeaders.getFirst("Authorization");
                    if (authToken.equals("afj232hj2332")) {
                        System.out.print("after authorization\n");
                        InputStream reqBody = exchange.getRequestBody();
                        String reqData = readString(reqBody);
                        System.out.print("data "+reqData+"\n");
                        Gson gson = new Gson();
                        JsonObject jsonObj = gson.fromJson(reqData, JsonObject.class);
                        String finalCmd = jsonObj.get("myCmd").getAsString();
                        String finalMsg = jsonObj.get("myMsg").getAsString();

                        Serializer ser = new Serializer();
                        BaseCommand baseCMD = ser.deserializeCommand(reqData,"com.example.alec.phase_05.Server.");
                        System.out.print(String.valueOf(baseCMD.getClass()));
                        success = true;
                        StringProcessor SpINIT = new StringProcessor();
                        StringProcessor SP = SpINIT.getInstance();
                        SP.setMyStr(finalMsg);
                        SP.setMyStr(finalCmd);




                        String respData = SP.executeBaseCommand(baseCMD,finalCmd,finalMsg);

                        Results myResults = new Results(success,respData,"none");
                        respData = ser.serialize(myResults);

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                        OutputStream respBody = exchange.getResponseBody();
                        writeString(respData, respBody);
                        respBody.close();
                    }
                }
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
