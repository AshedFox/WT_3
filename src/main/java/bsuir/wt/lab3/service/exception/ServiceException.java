package bsuir.wt.lab3.service.exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(Exception exception) {
        super(exception);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
