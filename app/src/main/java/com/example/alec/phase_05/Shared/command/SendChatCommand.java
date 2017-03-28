package com.example.alec.phase_05.Shared.command;

import com.example.alec.phase_05.Shared.model.Chat;

/**
 * Created by samuel on 3/25/17.
 */

public abstract class SendChatCommand extends GameCommand {
    private Chat chat;

    public SendChatCommand(Chat chat) {
        super("SendChat", chat.getName(), chat.getGameID());
        this.chat = chat;
    }

    public Chat getChat() {
        return chat;
    }
}
