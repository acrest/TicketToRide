package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.command.ICommand;

import java.util.List;

/**
 * Created by samuel on 2/14/17.
 */

public class CommandHolder {
    private List<ICommand> commands;

    public CommandHolder(List<ICommand> commands) {
        this.commands = commands;
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ICommand> commands) {
        this.commands = commands;
    }
}
