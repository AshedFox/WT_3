package bsuir.wt.lab3.controller.command.impl;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.service.ServiceFactory;
import bsuir.wt.lab3.service.exception.ServiceException;

public class UpdateCommand implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String[] data = request.split("[^,]\\s+");
        if (data.length != 3) {
            throw new CommandException("Invalid params count");
        }

        if (data[1].isEmpty()) {
            throw new CommandException("Id param can't be empty");
        }

        if (data[2].isEmpty()) {
            throw new CommandException("Archive File param can't be empty");
        }

        var archiveFileService = ServiceFactory.getInstance().getArchiveFileService();
        try {
            var archiveFile = ArchiveFile.parse(data[1]);

            archiveFileService.Update(Integer.parseInt(data[1]), archiveFile);
            return "";
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
