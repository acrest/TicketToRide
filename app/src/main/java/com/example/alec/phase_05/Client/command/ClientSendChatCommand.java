package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.SendChatCommand;
import com.example.alec.phase_05.Shared.model.Chat;

/**
 * Created by samuel on 3/25/17.
 */

public class ClientSendChatCommand extends SendChatCommand {
    public ClientSendChatCommand(Chat chat) {
        super(chat);
    }

    @Override
    public Result execute() {
        return null;
    }
}
