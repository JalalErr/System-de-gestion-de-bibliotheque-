package com.usermanager.dao.impl;

import com.usermanager.exception.DAOException;
import com.usermanager.model.ClientModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.*;

public class ClientDAOImpl {

    private final String Inserte_SQL = "INSERT INTO Adherants (Nom, Prenom, Email, Adress, Numero, Cin ) VALUES(?, ?, ?, ?, ?, ?) ";
    private final String Delete_SQL = "DELETE FROM Adherants WHERE cin = ? AND nom = ?";

    public ClientModel save(ClientModel user) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
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


    public boolean deletByCin(ClientModel user) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(Delete_SQL)) {

            preparedStatement.setString(1, user.getCin());
            preparedStatement.setString(2, user.getNom());
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Suppression client CIN: " + user.getCin() +
                    ", Nom: " + user.getNom() +
                    ", Lignes affectées: " + affectedRows);

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de la suppression du client CIN: " +
                    user.getCin() + " - " + e.getMessage(), e);
        }
    }
    private void setUserParameters(PreparedStatement preparedStatement, ClientModel user) throws SQLException {
        preparedStatement.setString(1, user.getNom());
        preparedStatement.setString(2, user.getPrenom());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getAdress());
        preparedStatement.setString(5, user.getNumero());
        preparedStatement.setString(6, user.getCin());
    }

}
