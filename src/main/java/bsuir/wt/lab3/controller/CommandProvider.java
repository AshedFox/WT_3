package bsuir.wt.lab3.controller;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.impl.*;
import bsuir.wt.lab3.entity.MessageType;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    final private Map<MessageType, Command> commands = new HashMap<>();

    CommandProvider() {
        commands.put(MessageType.AUTH, new AuthCommand());
        commands.put(MessageType.GET_ARCHIVE_FILE, new GetCommand());
        commands.put(MessageType.GET_ARCHIVE_FILES, new GetListCommand());
        commands.put(MessageType.POST_ARCHIVE_FILE, new PostCommand());
        commands.put(MessageType.UPDATE_ARCHIVE_FILE, new UpdateCommand());
        commands.put(MessageType.DELETE_ARCHIVE_FILE, new DeleteCommand());
    }

    Command getCommand(MessageType messageType) {
        return commands.get(messageType);
    }

}
