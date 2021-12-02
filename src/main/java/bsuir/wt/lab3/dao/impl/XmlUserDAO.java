package bsuir.wt.lab3.dao.impl;

import bsuir.wt.lab3.dao.UserDAO;
import bsuir.wt.lab3.dao.exception.DAOException;
import bsuir.wt.lab3.entity.User;
import bsuir.wt.lab3.entity.xml.XmlUsers;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class XmlUserDAO implements UserDAO {
    @Override
    public User Authorize(String email, String password) throws DAOException {
        File file = new File("src/main/resources/users.xml");

        List<User> users;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            XmlUsers xmlUsers = (XmlUsers) jaxbUnmarshaller.unmarshal(file);
            users = xmlUsers.getUsers();
        } catch (Exception e) {
            throw new DAOException("something wrong");
        }

        var user =
                users.stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst();

        if (user.isEmpty()) {
            throw new DAOException("user not found");
        }

        return user.get();
    }
}
