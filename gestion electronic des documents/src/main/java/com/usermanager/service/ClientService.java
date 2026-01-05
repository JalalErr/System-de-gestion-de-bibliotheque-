package com.usermanager.service;

import com.usermanager.dao.impl.ClientDAOImpl;
import com.usermanager.dao.impl.UserDAOImpl;
import com.usermanager.exception.DAOException;
import com.usermanager.model.ClientModel;

public class ClientService {

    UserDAOImpl userDAOImpl = new UserDAOImpl();
    ClientDAOImpl clientDAO = new ClientDAOImpl();

    public ClientModel create(ClientModel user) {
        //validateUser(user);
        //adherantDAO.save(user);
        System.out.println("Hello from User service");

        return clientDAO.save(user);
    }

    public ClientModel delete(ClientModel user) {

        System.out.println("Hello from Client Service");
        clientDAO.deletByCin(user);
        return user;
    }

    private void validateUser(ClientModel user) {
        if (user.getNom() == null || user.getNom().trim().isEmpty()) {
            throw new DAOException("Username is required");
        }

        if (user.getNom().length() < 3) {
            throw new DAOException("Username must be at least 3 characters");
        }

        if (!user.getNom().matches("^[a-zA-Z0-9_]+$")) {
            throw new DAOException("Username can only contain letters, numbers, and underscores");
        }

        if (user.getPrenom() == null || user.getPrenom().trim().isEmpty()) {
            throw new DAOException("Username is required");
        }

        if (user.getPrenom().length() < 3) {
            throw new DAOException("Username must be at least 3 characters");
        }

        if (!user.getPrenom().matches("^[a-zA-Z0-9_]+$")) {
            throw new DAOException("Username can only contain letters, numbers, and underscores");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new DAOException("Email is required");
        }

        if (!isValidEmail(user.getEmail())) {
            throw new DAOException("Invalid email format");
        }


    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
