package com.example.alec.phase_05.Client.command;

import com.example.alec.phase_05.Client.ClientFacade;
import com.example.alec.phase_05.Shared.command.ChatSentCommand;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.model.Chat;

/**
 * Created by samuel on 3/25/17.
 */

public class ClientChatSentCommand extends ChatSentCommand {
    public ClientChatSentCommand(Chat chat) {
        super(chat);
    }

    @Override
    public Result execute() {
        ClientFacade.getInstance().chatSent(getChat());
        return null;
    }


}
