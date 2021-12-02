package bsuir.wt.lab3.controller.command.exception;

public class CommandException extends Exception {
    public CommandException() {
        super();
    }

    public CommandException(Exception exception) {
        super(exception);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Exception exception) {
        super(message, exception);
    }
}
