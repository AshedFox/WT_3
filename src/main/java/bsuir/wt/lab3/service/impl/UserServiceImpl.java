package bsuir.wt.lab3.service.impl;

import bsuir.wt.lab3.dao.DAOFactory;
import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.User;
import bsuir.wt.lab3.service.UserService;
import bsuir.wt.lab3.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User Authorize(String email, String password) throws ServiceException {
        var userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.Authorize(email, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
