package bsuir.wt.lab3.controller.command.impl;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.service.ServiceFactory;
import bsuir.wt.lab3.service.exception.ServiceException;

public class AuthCommand implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String[] data = request.split("\\s+");

        if (data.length != 3) {
            throw new CommandException("Invalid params count");
        }

        if (data[1].isEmpty()) {
            throw new CommandException("Email param can't be empty");
        }

        var userService = ServiceFactory.getInstance().getUserService();
        try {
            var user = userService.Authorize(data[1], data[2]);
            return user.toString();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
