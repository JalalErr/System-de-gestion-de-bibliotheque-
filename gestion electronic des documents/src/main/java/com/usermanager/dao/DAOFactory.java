package com.usermanager.dao;

import com.usermanager.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static DAOFactory instance;

    private UserDAO userDAO;

    private DAOFactory() {
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }
    public synchronized UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }


}