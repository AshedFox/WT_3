package bsuir.wt.lab3.dao.exception;

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(Exception exception) {
        super(exception);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception exception) {
        super(message, exception);
    }
}
