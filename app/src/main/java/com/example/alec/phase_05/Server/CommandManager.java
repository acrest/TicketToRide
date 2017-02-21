package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by Andrew on 2/20/2017.
 */
public class CommandManager {
    private static CommandManager ourInstance = new CommandManager();
    Map<String, Integer> playerIndex = new TreeMap<>();
    public static CommandManager getInstance() {
        return ourInstance;
    }

    private CommandManager() {
    }

    public void addCommand(BaseCommand command){
        commands.add(command);
    }

    private List<BaseCommand> commands = new ArrayList<>();

    public List<BaseCommand> recentCommands(Player player){
        List<BaseCommand> recCommands = new ArrayList<>();
        int commandIndex = getCommandIndex(player);

        for(int i = commandIndex; i < commands.size(); i++)
        {
            if(commands.get(i).getId() == player.getId() || commands.get(i).getId() == -1)    //Command id and player id aren't the same.
            {
                recCommands.add(commands.get(i));
            }
        }

        return recCommands;
    }

    private int getCommandIndex(Player player){
        if(!playerIndex.containsKey(player.getName()))
        {
            playerIndex.put(player.getName(), 0);
        }

        return playerIndex.get(player.getName());
    }
}
