package com.usermanager.dao.impl;

import com.usermanager.exception.DAOException;
import com.usermanager.model.AdminModel;
import com.usermanager.model.UserModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.*;


public class AdherantDAO {

    private final String Inserte_SQL = "INSERT INTO Adherants (Nom, Prenom, Email, Adress, Numero, Cin ) VALUES(?, ?, ?, ?, ?, ?) ";

    public UserModel save(UserModel user) {

        try ( Connection conn = DatabaseConnection.getInstance().getConnection();
              PreparedStatement preparedStatement = conn.prepareStatement(Inserte_SQL, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Hello from Adherant DAO");
            setUserParameters(preparedStatement, user);
            System.out.println("Hello from Adherant DAO 2");

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("After execution of request rows adherantDAO \n");
            if (affectedRows == 0) {
                throw new DAOException("Creating user failed, no rows affected");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new DAOException("Creating user failed, no ID obtained");
                }
            }
        }catch (Exception e){
            throw new DAOException("Error saving user: " + e.getMessage(), e);
        }
    }

    private void setUserParameters(PreparedStatement preparedStatement, UserModel user) throws SQLException {
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getAdress());
        preparedStatement.setString(5, user.getNumero());
        preparedStatement.setString(6, user.getCin());
    }

}
