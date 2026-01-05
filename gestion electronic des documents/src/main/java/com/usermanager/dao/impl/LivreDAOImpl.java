package com.usermanager.dao.impl;

import com.usermanager.exception.DAOException;
import com.usermanager.model.LivreModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class LivreDAOImpl {

    private final String INSERT_SQL =
            "INSERT INTO livre (titre, auteur, isbn, disponible) VALUES (?, ?, ?, ?)";

    public void saveBook(LivreModel livre) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getIsbn());
            ps.setBoolean(4, livre.isDisponible());

            int affectRows = ps.executeUpdate();
            System.out.println("Rows affected ---> " + affectRows);

        }catch (SQLException e){
            throw new DAOException("Erreur lors de l'enregistrement du Livre");

        }

    }
}
