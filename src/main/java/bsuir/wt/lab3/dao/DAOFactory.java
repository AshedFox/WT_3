package bsuir.wt.lab3.dao;

import bsuir.wt.lab3.dao.impl.XmlArchiveFileDAO;
import bsuir.wt.lab3.dao.impl.XmlUserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final ArchiveFileDAO archiveFileDAO = new XmlArchiveFileDAO();
    private final UserDAO userDAO = new XmlUserDAO();

    public static DAOFactory getInstance() {
        return instance;
    }

    public ArchiveFileDAO getArchiveFileDAO() {
        return archiveFileDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
