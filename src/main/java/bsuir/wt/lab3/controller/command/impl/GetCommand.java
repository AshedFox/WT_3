package bsuir.wt.lab3.controller.command.impl;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.service.ServiceFactory;
import bsuir.wt.lab3.service.exception.ServiceException;

public class GetCommand implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String[] data = request.split("\\s+");

        if (data.length != 2) {
            throw new CommandException("Invalid params count");
        }

        if (data[1].isEmpty()) {
            throw new CommandException("Id param can't be empty");
        }

        var archiveFileService = ServiceFactory.getInstance().getArchiveFileService();
        try {
            var archiveFile = archiveFileService.Read(Integer.parseInt(data[1]));
            return archiveFile.toString();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
