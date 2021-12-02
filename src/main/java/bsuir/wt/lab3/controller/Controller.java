package bsuir.wt.lab3.controller;

import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.entity.MessageType;
import bsuir.wt.lab3.entity.Role;
import bsuir.wt.lab3.entity.User;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public String doAction(String request, User user) {
        String[] data = request.split("\\s+");

        try {
            var messageType = MessageType.valueOf(data[0]);

            switch (messageType) {
                case AUTH, GET_ARCHIVE_FILE, GET_ARCHIVE_FILES -> {
                    var command = commandProvider.getCommand(messageType);

                    try {
                        return messageType + " " + command.execute(request);
                    } catch (CommandException e) {
                        return "ERROR" + " " + e.getMessage();
                    }
                }
                case POST_ARCHIVE_FILE, UPDATE_ARCHIVE_FILE, DELETE_ARCHIVE_FILE -> {
                    if (user != null && user.getRole() == Role.ADMIN) {
                        var command = commandProvider.getCommand(messageType);

                        try {
                            return messageType + " " + command.execute(request);
                        } catch (CommandException e) {
                            return "ERROR" + " " + e.getMessage();
                        }
                    }
                    else {
                        return "ERROR No access";
                    }
                }
                default -> {
                    return "";
                }
            }
        } catch (IllegalArgumentException e) {
            return "Error, illegal messageType";
        }
    }
}
