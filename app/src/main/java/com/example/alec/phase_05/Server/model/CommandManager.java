package com.example.alec.phase_05.Server.model;

import com.example.alec.phase_05.Client.command.ClientPickedTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerChatSentCommand;
import com.example.alec.phase_05.Server.command.ServerClaimRouteCommand;
import com.example.alec.phase_05.Server.command.ServerClaimedRouteCommand;
import com.example.alec.phase_05.Server.command.ServerDiscardTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerDrawDestinationCardCommand;
import com.example.alec.phase_05.Server.command.ServerDrawTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerDrawnDestinationCardCommand;
import com.example.alec.phase_05.Server.command.ServerDrawnTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerFinishGameCommand;
import com.example.alec.phase_05.Server.command.ServerFinishTurnCommand;
import com.example.alec.phase_05.Server.command.ServerFinishedTurnCommand;
import com.example.alec.phase_05.Server.command.ServerGameFinishedCommand;
import com.example.alec.phase_05.Server.command.ServerGameStartedCommand;
import com.example.alec.phase_05.Server.command.ServerPickTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerPickedTrainCardCommand;
import com.example.alec.phase_05.Server.command.ServerReturnDestinationCardCommand;
import com.example.alec.phase_05.Server.command.ServerReturnedDestinationCard;
import com.example.alec.phase_05.Server.command.ServerSendChatCommand;
import com.example.alec.phase_05.Shared.command.GameCommand;
import com.example.alec.phase_05.Shared.command.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void addCommand(GameCommand command) {
        commands.add(command);
    }

    public ICommand recentCommand(String playerName) {
        int commandIndex = getCommandIndex(playerName);
        ICommand command;
        if (commandIndex == -1) {
            command = new ServerGameStartedCommand(GameStateFactory.gameToGameState(game));
            setCommandIndex(playerName, commands.size());
        } else {
            if(commandIndex == commands.size()) {
                command = null;
            } else {
                command = createCorrespondingCommand(commands.get(commandIndex));
                setCommandIndex(playerName, commandIndex + 1);
            }
        }
        return command;
    }

//    public List<BaseCommand> recentCommands(String playerName) {
//        System.out.println("called recentCommands with " + player.getName());
//        List<BaseCommand> recCommands = new ArrayList<>();
//        int commandIndex = getCommandIndex(player);
//        System.out.println("command index for player is " + commandIndex);
//        System.out.println("command size is " + commands.size());

//        if(commandIndex == 0) {
//            GameInfo gameState = GameStateFactory.gameToGameState(game);
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

//        System.out.println("recentCommands called");
//        if (hasServerStartGame()) { //temporary code
//            GameInfo state = GameStateFactory.gameToGameState(game);
//            if (state == null) {
//                System.out.println("got null game state");
//                System.out.println("game = " + game);
//            } else {
//                System.out.println("game state not null");
//            }
//            recCommands.add(new ServerGameStartedCommand(state));
//        }
//        return recCommands;
//    }

//    private boolean hasServerStartGame() {
//        for (GameCommand command : commands) {
//            if (command instanceof ServerStartGameCommand) return true;
//        }
//        return false;
//    }

    private ICommand createCorrespondingCommand(GameCommand command) {
        if (command instanceof ServerClaimRouteCommand) {
            return new ServerClaimedRouteCommand(command.getPlayerName(), ((ServerClaimRouteCommand) command).getRouteId());
        } else if (command instanceof ServerDiscardTrainCardCommand) {
            return null; //not sure if we are going to use this command
        } else if (command instanceof ServerDrawDestinationCardCommand) {
            return new ServerDrawnDestinationCardCommand(command.getPlayerName());
        } else if (command instanceof ServerDrawTrainCardCommand) {
            return new ServerDrawnTrainCardCommand(command.getPlayerName());
        } else if (command instanceof ServerFinishTurnCommand) {
            return new ServerFinishedTurnCommand(command.getPlayerName());
        } else if (command instanceof ServerPickTrainCardCommand) {
            int cardIndex = ((ServerPickTrainCardCommand) command).getCardIndex();
            return new ServerPickedTrainCardCommand(command.getPlayerName(), cardIndex, game.getVisibleCard(cardIndex));
        } else if (command instanceof ServerReturnDestinationCardCommand) {
            return new ServerReturnedDestinationCard(command.getPlayerName());
        } else if (command instanceof ServerSendChatCommand) {
            return new ServerChatSentCommand(((ServerSendChatCommand) command).getChat());
        } else if (command instanceof ServerFinishGameCommand) {
            return new ServerGameFinishedCommand();
        }

        return null;
    }

    private int getCommandIndex(String playerName) {
        if (!playerIndex.containsKey(playerName)) {
            playerIndex.put(playerName, -1);
        }

        return playerIndex.get(playerName);
    }

    private void setCommandIndex(String playerName, int index) {
        playerIndex.put(playerName, index);
    }
}
