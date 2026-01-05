package com.usermanager.dao.impl;

import com.usermanager.model.DocumentType;
import com.usermanager.model.EmpruntModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmpruntDAO {

    private final String INSERT_SQL =
            "INSERT INTO emprunt (adherent_id, document_type, document_id, date_emprunt, date_retour_prevue) " +
                    "SELECT id, ?, ?, ?, ? " +
                    "FROM adherants " +
                    "WHERE Cin = ?";

    //private final String INSERT_SQL = "INSERT INTO emprunt (adherent_id, document_type, document_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?, ?)";

    private final String SELECT_SQL = "SELECT * FROM emprunt";

    private final String UPDATE_SQL = "UPDATE emprunt SET date_retour_effective = CURRENT_DATE WHERE id = ? ";
    // Créer un emprunt
    public void save(EmpruntModel emprunt) throws SQLException {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedstatment = conn.prepareStatement(INSERT_SQL,  Statement.RETURN_GENERATED_KEYS)){

            //preparedstatment.setInt(1, emprunt.getAdherant_id());
//            preparedstatment.setString(7, emprunt.getType());
//            preparedstatment.setString(1, emprunt.getType().name());
//            preparedstatment.setInt(2, emprunt.getDocumentId());
//            preparedstatment.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
//            preparedstatment.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
//            preparedstatment.setString(5, emprunt.getAdherantCin());

            preparedstatment.setString(1, emprunt.getType().name());
            preparedstatment.setInt(2, emprunt.getDocumentId());
            preparedstatment.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            preparedstatment.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            preparedstatment.setString(5, emprunt.getAdherantCin());

            preparedstatment.executeUpdate();

        }catch (Exception e) {
            System.out.println(e);
        }
    }

    // Lister tous les emprunts
    public List<EmpruntModel> findAll() throws SQLException {
        List<EmpruntModel> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedstatment = conn.prepareStatement(SELECT_SQL,  Statement.RETURN_GENERATED_KEYS)){

            ResultSet rs = preparedstatment.executeQuery(SELECT_SQL);

            while (rs.next()) {
                EmpruntModel emprunt = new EmpruntModel();
                emprunt.setId(rs.getInt("id"));
                emprunt.setAdherant_id(rs.getInt("adherant_id"));
                emprunt.setType(DocumentType.valueOf(rs.getString("document_type")) );
                emprunt.setDocumentId(rs.getInt("document_id"));
                emprunt.setDateEmprunt(rs.getDate("date_emprunt").toLocalDate());
                emprunt.setDateRetourPrevue(rs.getDate("date_retour_prevue").toLocalDate());

                Date d = rs.getDate("date_retour_effective");
                if (d != null) {
                    emprunt.setDateRetourEffective(d.toLocalDate());
                }

                list.add(emprunt);
            }
            return list;

        }catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // Retourner un document
    public void retourner(int empruntId) throws SQLException {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedstatment = conn.prepareStatement(UPDATE_SQL,  Statement.RETURN_GENERATED_KEYS)){

            preparedstatment.setInt(1, empruntId);
            preparedstatment.executeUpdate();
    }catch (Exception e) {
        System.out.println(e);
        }
    }
}
