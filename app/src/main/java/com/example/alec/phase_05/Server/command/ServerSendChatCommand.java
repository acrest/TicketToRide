package com.example.alec.phase_05.Server.command;
import java.io.Serializable;

import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Shared.command.Result;
import com.example.alec.phase_05.Shared.command.SendChatCommand;
import com.example.alec.phase_05.Shared.model.Chat;

/**
 * Created by samuel on 3/25/17.
 */

public class ServerSendChatCommand extends SendChatCommand implements Serializable {
    public ServerSendChatCommand(Chat chat) {
        super(chat);
    }

    @Override
    public Result execute() {
        Result result = new ServerResult();
        result.setResultObject(ServerFacade.getInstance().sendChat(getChat()));
        return result;
    }

    @Override
    public void reExecute() {
        ServerFacade.getInstance().sendChat(getChat());
    }
}
