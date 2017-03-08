package com.example.alec.phase_05.Server;

import android.util.Log;

import com.example.alec.phase_05.Server.command.ServerDrawnDestinationCardCommand;
import com.example.alec.phase_05.Server.command.ServerGameStartedCommand;
import com.example.alec.phase_05.Server.command.ServerStartGameCommand;
import com.example.alec.phase_05.Server.model.GameStateFactory;
import com.example.alec.phase_05.Server.model.IServerGame;
import com.example.alec.phase_05.Shared.command.BaseCommand;
import com.example.alec.phase_05.Shared.command.DrawDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.DrawTrainCardCommand;
import com.example.alec.phase_05.Shared.command.DrawnDestinationCardCommand;
import com.example.alec.phase_05.Shared.command.DrawnTrainCardCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;
import com.example.alec.phase_05.Shared.command.StartGameCommand;
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

    public List<BaseCommand> recentCommands(String playerName){
//        System.out.println("called recentCommands with " + player.getName());
        List<BaseCommand> recCommands = new ArrayList<>();
//        int commandIndex = getCommandIndex(player);
//        System.out.println("command index for player is " + commandIndex);
//        System.out.println("command size is " + commands.size());

//        if(commandIndex == 0) {
//            GameState gameState = GameStateFactory.gameToGameState(game);
//            ServerGameStartedCommand command = new ServerGameStartedCommand(gameState);
//            recCommands.add(command);
//        }

//        for(int i = commandIndex; i < commands.size(); i++)
//        {
//            if(commands.get(i).getPlayerId() == player.getId() || commands.get(i).getId() == -1)
//            {
////                recCommands.add(commands.get(i));
//                System.out.println("Added COMMMMMMMMMMMMMAND");
//                recCommands.add(createNeededCommand(commands.get(i)));
//            }
//        }

//        playerIndex.put(player.getName(), commands.size());
//

        System.out.println("recentCommands called");
        if(hasServerStartGame()) { //temporary code
            GameState state = GameStateFactory.gameToGameState(game);
            if(state == null) {
                System.out.println("got null game state");
                System.out.println("game = " + game);
            } else {
                System.out.println("game state not null");
            }
            recCommands.add(new ServerGameStartedCommand(state));
        }
        return recCommands;
    }

    private boolean hasServerStartGame() {
        for(GameCommand command : commands) {
            if(command instanceof ServerStartGameCommand) return true;
        }
        return false;
    }

    private ICommand createNeededCommand(GameCommand command) {
        if(command instanceof ServerStartGameCommand) {
            return new ServerGameStartedCommand(GameStateFactory.gameToGameState(game));
        }

        return null;
    }

    private int getCommandIndex(String playerName){
        if(!playerIndex.containsKey(playerName))
        {
            playerIndex.put(playerName, 0);
        }

        return playerIndex.get(playerName);
    }
}
