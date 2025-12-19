package com.usermanager.dao.impl;

import com.usermanager.model.UserModel;
import com.usermanager.dao.UserDAO;
import com.usermanager.model.UserRole;
import com.usermanager.exception.DAOException;
import com.usermanager.util.DatabaseConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO{

    List<String> data;

    private static final String INSERT_SQL =
            "INSERT INTO comptes (nom, prenom, email, password, role) VALUES (?, ?, ?, ?, ?)";

    private static final String AUTH_SQL =
            "SELECT * FROM comptes WHERE email = ?";

    private static final String USERS_SQL =
            "SELECT * FROM comptes WHERE id = ?";

    @Override
    public UserModel save(UserModel user) {

        System.out.println("UserDAOImpl query execute\n");
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Testing query for execute\n");

            setUserParameters(pstmt, user);

            int affectedRows = pstmt.executeUpdate();
            System.out.printf("Affecting rows \n", affectedRows);

            System.out.println("Testing query after execute\n");

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
    public Optional<UserModel> authenticate(String email, String password) {
        System.out.println("Hello From UserDAOImpl");

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(AUTH_SQL)) {

            pstmt.setString(1, email);

            System.out.println("Hello From UserDAOImpl after executing query");

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println("Hello From UserDAOImpl if");

                    UserModel user = new UserModel();
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(UserRole.valueOf(rs.getString("role")));

                    System.out.println(
                            user.getNom() + " | " +
                                    user.getPrenom() + " | " +
                                    user.getEmail() + " | " +
                                    user.getRole()
                    );

                    return Optional.of(user);
                }

                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new DAOException("Authentication error", e);
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
}