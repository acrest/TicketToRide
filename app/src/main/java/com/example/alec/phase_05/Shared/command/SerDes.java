package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Client.command.ClientResult;
import com.example.alec.phase_05.Shared.model.GameDescription;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Utility class containing methods to serialize and deserialize object to and from json strings.
 * Created by samuel on 2/3/17.
 */
public class SerDes
{
    /**
     * Takes the given json string and deserializes it into a command.
     * The command class is given by prefix + BaseCommand::getCommandName() + suffix.
     * @param json serialized command
     * @param prefix package name and any other prefix needed before the name of the command
     * @param suffix suffix following the name of the command
     * @return deserialize command
     */
    public static ICommand deserializeCommand(String json, String prefix, String suffix)
    {
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
        String commandName = jsonObj.get("commandName").getAsString();

        String classname = prefix + commandName + suffix;

        BaseCommand baseCommand = null;
        try {
            Class c;
            try {
                c = Class.forName(classname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            baseCommand = (BaseCommand) gson.fromJson(json, c);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return baseCommand;
    }

    /**
     * Serializes obj into a json string.
     * @param obj object to serialize
     * @return json representation
     */
    public static String serialize(Object obj)
    {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String serialize(Object obj, Type type) {
        Gson gson = new Gson();
        return gson.toJson(obj, type);
    }

    /**
     * Deserialize the given json string into a Result
     * @param json json string to deserialize
     * @return deserialized Result
     */
    public static Result deserializeResult(String json)
    {
        return (Result) deserializeToClass(json, Result.class);
    }

    public static ClientResult deserializeClientResult(String json)
    {
        Object obj = deserializeToClass(json, ClientResult.class);
        return (ClientResult) obj;
    }

    /**
     * Deserialize the given json string into the given class
     * @param json json string to deserialize
     * @param type class to deserialize to
     * @return deserialized object
     */
    public static Object deserializeToClass(String json, Class<?> type)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static Object deserializeToType(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
