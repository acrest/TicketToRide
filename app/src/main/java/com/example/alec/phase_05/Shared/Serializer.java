package com.example.alec.phase_05.Shared;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Serializer {

    public static BaseCommand deserializeCommand(String json, String packagePrefix)
    {
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
        String commandname = jsonObj.get("myCmd").getAsString();

        String classname = packagePrefix + commandname + "Command";

        BaseCommand basecmd = null;
        try {
            Class c = null;
            try {
                c = Class.forName(classname);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            basecmd = (BaseCommand)gson.fromJson(json, c);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return basecmd;
    }

    public static String serialize(Object c)
    {
        Gson gson = new Gson();
        return gson.toJson(c);
    }

    public static Results deserializeResults(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Results.class);
    }

    public static Arguments deserializeArguments(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Arguments.class);
    }

}
