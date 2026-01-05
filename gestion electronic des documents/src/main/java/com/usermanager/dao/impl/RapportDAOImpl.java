package com.usermanager.dao.impl;

import com.usermanager.exception.DAOException;
import com.usermanager.model.RapportModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RapportDAOImpl {

    private final String INSERT_SQL =
            "INSERT INTO rapport (nomEtudiant, nomEncadrant, sujet, ecole, disponible) VALUES (?, ?, ?, ?, ?)";

    public void saveRapport(RapportModel rapport)  {

      try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

          ps.setString(1, rapport.getNomEtudiant());
          ps.setString(2, rapport.getnomEncadrant());
          ps.setString(3, rapport.getSujet());
          ps.setString(4, rapport.getEcole());
          ps.setBoolean(5, rapport.getDisponible());

          int affRows = ps.executeUpdate();
          System.out.println("Rows affected ---> " + affRows);

      }catch (SQLException e) {
          throw new DAOException("Erreur lors de l'enregistrement");
      }
    }
}
