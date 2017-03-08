package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.command.ICommand;

import java.util.List;

/**
 * Created by samuel on 2/14/17.
 */

public class CommandHolder {
    private List<BaseCommand> commands;

    public CommandHolder(List<BaseCommand> commands) {
        this.commands = commands;
    }

    public List<BaseCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<BaseCommand> commands) {
        this.commands = commands;
    }
}
