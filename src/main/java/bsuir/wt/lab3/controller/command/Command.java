package bsuir.wt.lab3.controller.command;

import bsuir.wt.lab3.controller.command.exception.CommandException;

public interface Command {
    String execute(String request) throws CommandException;
}
