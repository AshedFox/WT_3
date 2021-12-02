package bsuir.wt.lab3.controller.command.impl;

import bsuir.wt.lab3.controller.command.Command;
import bsuir.wt.lab3.controller.command.exception.CommandException;
import bsuir.wt.lab3.entity.ArchiveFile;
import bsuir.wt.lab3.entity.Student;
import bsuir.wt.lab3.service.ServiceFactory;
import bsuir.wt.lab3.service.exception.ServiceException;

import java.util.Random;

public class PostCommand implements Command {
    @Override
    public String execute(String request) throws CommandException {
        String[] data = request.split("[^,]\\s+");
        if (data.length != 2) {
            throw new CommandException("Invalid params count");
        }

        if (data[1].isEmpty()) {
            throw new CommandException("Code param can't be empty");
        }


        var archiveFileService = ServiceFactory.getInstance().getArchiveFileService();
        try {
            var student = Student.parse(data[1]);
            var archiveFile = new ArchiveFile();
            archiveFile.setStudent(student);
            archiveFile.setCode("1" + String.valueOf(student.getName().charAt(0)) +
                    String.valueOf(student.getSurname().charAt(0)) +  String.valueOf(student.getAge()) + "1");

            archiveFileService.Create(archiveFile);
            return "";
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
