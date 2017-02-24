package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
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
    Map<String, Integer> playerIndex = new TreeMap<>();
    private List<GameCommand> commands = new ArrayList<>();


    public CommandManager() {
    }

    public void addCommand(GameCommand command){
        commands.add(command);
    }

    public List<GameCommand> recentCommands(Player player){
        List<GameCommand> recCommands = new ArrayList<>();
        int commandIndex = getCommandIndex(player);

        for(int i = commandIndex; i < commands.size(); i++)
        {
            if(commands.get(i).getPlayerId() == player.getId() || commands.get(i).getId() == -1)
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
