package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Server.command.ServerDrawnDestinationCardCommand;
import com.example.alec.phase_05.Server.command.ServerGameStartedCommand;
import com.example.alec.phase_05.Server.model.GameStateFactory;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.command.DrawnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.DrawnTrainCardCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.model.GameState;
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
    private Map<String, Integer> playerIndex;
    private List<GameCommand> commands;
    private IServerGame game;


    public CommandManager() {
        playerIndex = new TreeMap<>();
        commands = new ArrayList<>();
        game = null;
    }

    public void setGame(IServerGame game) {
        this.game = game;
    }

    public void addCommand(GameCommand command){
        commands.add(command);
    }

    public List<ICommand> recentCommands(Player player){
        List<ICommand> recCommands = new ArrayList<>();
        int commandIndex = getCommandIndex(player);

//        if(commandIndex == 0) {
//            GameState gameState = GameStateFactory.gameToGameState(game);
//            ServerGameStartedCommand command = new ServerGameStartedCommand(gameState);
//            recCommands.add(command);
//        }

        for(int i = commandIndex; i < commands.size(); i++)
        {
            if(commands.get(i).getPlayerId() == player.getId() || commands.get(i).getId() == -1)
            {
//                recCommands.add(commands.get(i));
//                recCommands.add(createNeededCommand(commands.get(i)));
            }
        }

        return recCommands;
    }

//    private ICommand createNeededCommand(GameCommand command) {
//        if(command instanceof DrawDestinationCardCommand) {
//            DrawDestinationCardCommand cmd = (DrawDestinationCardCommand) command;
//            return new ServerDrawnDestinationCardCommand(cmd.getUserName(), cmd.get);
//        } else if(command instanceof DrawTrainCardCommand) {
//
//        }
//    }

    private int getCommandIndex(Player player){
        if(!playerIndex.containsKey(player.getName()))
        {
            playerIndex.put(player.getName(), 0);
        }

        return playerIndex.get(player.getName());
    }
}
