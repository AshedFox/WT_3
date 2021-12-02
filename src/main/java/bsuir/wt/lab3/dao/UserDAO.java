package bsuir.wt.lab3.dao;

import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.User;

public interface UserDAO {
    User Authorize(String email, String password) throws DAOException;
}
