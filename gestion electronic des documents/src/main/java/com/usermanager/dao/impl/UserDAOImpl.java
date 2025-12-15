package com.usermanager.dao.impl;

import com.usermanager.model.UserModel;
import com.usermanager.dao.UserDAO;
import com.usermanager.model.UserRole;
import com.usermanager.exception.DAOException;
import com.usermanager.util.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO{

    private static final String INSERT_SQL = "INSERT INTO users (nom, prenom, email, password, role) VALUES (?, ?, ?, ?, ?)";

    @Override
    public UserModel save(UserModel user) {
        System.out.printf("UserDAOImpl query execute\n");
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            System.out.printf("Testing query for execute\n");

            setUserParameters(pstmt, user);
            System.out.printf("User param : \n", pstmt, user);

            int affectedRows = pstmt.executeUpdate();
            System.out.printf("Affecting rows", affectedRows);

            System.out.printf("Testing query after execute\n");

            if (affectedRows == 0) {
                throw new DAOException("Creating user failed, no rows affected");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new DAOException("Creating user failed, no ID obtained");
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error saving user: " + e.getMessage(), e);
        }
    }

    @Override
    public UserModel update(UserModel entity) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Optional<UserModel> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<UserModel> findAll() {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    private void setUserParameters(PreparedStatement pstmt, UserModel user) throws SQLException {
        pstmt.setString(1, user.getNom());
        pstmt.setString(2, user.getPrenom());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPassword());
        pstmt.setString(5, user.getRole().name());
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<UserModel> findByRole(UserRole role) {
        return List.of();
    }

    @Override
    public List<UserModel> searchByName(String keyword) {
        return List.of();
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }
}
