package com.example.alec.phase_05.Server.command;

import com.example.alec.phase_05.Shared.command.ChatSentCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Chat;

import java.io.Serializable;

/**
 * Created by samuel on 3/25/17.
 */

public class ServerChatSentCommand extends ChatSentCommand implements Serializable {
    public ServerChatSentCommand(Chat chat) {
        super(chat);
    }

    @Override
    public Result execute() {
        return null;
    }

    @Override
    public void reExecute() {

    }
}
