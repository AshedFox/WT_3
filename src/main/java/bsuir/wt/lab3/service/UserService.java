package bsuir.wt.lab3.service;

import bsuir.wt.lab3.entity.User;
import bsuir.wt.lab3.service.exception.ServiceException;

public interface UserService {
    User Authorize(String email, String password) throws ServiceException;
}
