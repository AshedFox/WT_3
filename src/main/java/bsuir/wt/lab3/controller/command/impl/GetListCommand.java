package bsuir.wt.lab3.controller.command.impl;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.service.ServiceFactory;
import bsuir.wt.lab3.service.exception.ServiceException;

public class GetListCommand implements Command {
    @Override
    public String execute(String request) throws CommandException {
        var archiveFileService = ServiceFactory.getInstance().getArchiveFileService();
        try {
            var archiveFiles = archiveFileService.ReadList();
            return archiveFiles.toString();
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
