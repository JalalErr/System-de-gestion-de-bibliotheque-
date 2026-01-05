package com.usermanager.dao.impl;

import com.usermanager.dao.SanctionDAO;
import com.usermanager.model.SanctionModel;
import com.usermanager.util.DatabaseConnection;
import com.usermanager.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanctionDAOImpl implements SanctionDAO {

    private static final String INSERT_SQL =
            "INSERT INTO sanction (emprunt_id, raison, active) VALUES (?, ?, ?)";
    private static final String EXISTS_SQL =
            "SELECT COUNT(*) FROM sanction WHERE emprunt_id = ? AND active = TRUE";
    private static final String FIND_ALL_SQL =
            "SELECT s.id, s.emprunt_id, s.raison, s.date_sanction, s.active,\n" +
                    "       a.nom AS adherent_nom, a.prenom AS adherent_prenom\n" +
                    "FROM sanction s\n" +
                    "JOIN emprunt e ON s.emprunt_id = e.id\n" +
                    "JOIN adherants a ON e.adherent_id = a.id\n" +
                    "ORDER BY s.date_sanction DESC;";
    private static final String INSERT_SQL_ =
            "INSERT INTO sanction (emprunt_id, raison, date_sanction, active) VALUES (?, ?, ?, ?)";



    @Override
    public void save(SanctionModel sanction) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setInt(1, sanction.getEmpruntId());
            pstmt.setString(2, sanction.getRaison());
            pstmt.setBoolean(3, sanction.isActive());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error saving sanction", e);
        }
    }

    @Override
    public boolean existsByEmpruntId(int empruntId) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(EXISTS_SQL)) {

            pstmt.setInt(1, empruntId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error checking sanction existence", e);
        }
        return false;
    }

    @Override
    public List<SanctionModel> findAll() {
        List<SanctionModel> sanctions = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SanctionModel s = new SanctionModel();
                s.setId(rs.getInt("id"));
                s.setEmpruntId(rs.getInt("emprunt_id"));
                s.setRaison(rs.getString("raison"));
                s.setActive(rs.getBoolean("active"));
                s.setDateSanction(rs.getDate("date_sanction").toLocalDate());
                s.setAdherentNom(rs.getString("adherent_nom"));
                s.setAdherentPrenom(rs.getString("adherent_prenom"));

                sanctions.add(s);
            }

        } catch (SQLException e) {
            throw new DAOException("Error loading sanctions", e);
        }
        return sanctions;
    }

    @Override
    public void addSanction(SanctionModel sanction) {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL_)) {

            ps.setInt(1, sanction.getEmpruntId());
            ps.setString(2, sanction.getRaison());
            ps.setDate(3,java.sql.Date.valueOf(sanction.getDateSanction()));
            ps.setBoolean(4, sanction.isActive());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Erreur lors de l'ajout de la sanction", e);
        }
    }
}
