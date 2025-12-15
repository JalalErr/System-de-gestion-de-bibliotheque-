package com.usermanager.service;

import com.usermanager.dao.DAOFactory;
import com.usermanager.dao.UserDAO;
import com.usermanager.exception.DuplicateDataException;
import com.usermanager.model.UserModel;
import com.usermanager.model.UserRole;
import com.usermanager.exception.DAOException;

import java.util.List;
import java.util.Optional;


public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = DAOFactory.getInstance().getUserDAO();
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserModel createUser(UserModel user) {
        System.out.println("User Service");
        //validateUser(user);
        System.out.println("User Service after validation");

        if (userDAO.existsByUsername(user.getNom())) {
            throw new DuplicateDataException("Username already exists: " + user.getNom());
        }

        if (userDAO.existsByEmail(user.getEmail())) {
            throw new DuplicateDataException("Email already exists: " + user.getEmail());
        }

        return userDAO.save(user);
    }


    public boolean deleteUser(Integer id) {
        return userDAO.delete(id);
    }

    public Optional<UserModel> getUserById(Integer id) {
        return userDAO.findById(id);
    }

    public Optional<UserModel> getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public List<UserModel> getAllUsers() {
        return userDAO.findAll();
    }

    public List<UserModel> getUsersByRole(UserRole role) {
        return userDAO.findByRole(role);
    }

    public List<UserModel> searchUsers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllUsers();
        }
        return userDAO.searchByName(keyword);
    }

    public boolean authenticateUser(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new DAOException("Username is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new DAOException("Password is required");
        }
        return userDAO.authenticate(username, password);
    }

    public long getTotalUserCount() {
        return userDAO.count();
    }

    private void validateUser(UserModel user) {
        if (user.getNom() == null || user.getNom().trim().isEmpty()) {
            throw new DAOException("Username is required");
        }

        if (user.getNom().length() < 3) {
            throw new DAOException("Username must be at least 3 characters");
        }

        if (!user.getNom().matches("^[a-zA-Z0-9_]+$")) {
            throw new DAOException("Username can only contain letters, numbers, and underscores");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new DAOException("Email is required");
        }

        if (!isValidEmail(user.getEmail())) {
            throw new DAOException("Invalid email format");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new DAOException("Password is required");
        }

        if (user.getPassword().length() < 6) {
            throw new DAOException("Password must be at least 6 characters");
        }

        if (user.getRole() == null) {
            throw new DAOException("Role is required");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}
