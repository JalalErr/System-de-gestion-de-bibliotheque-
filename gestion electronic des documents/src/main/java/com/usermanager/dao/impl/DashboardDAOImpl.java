package com.usermanager.dao.impl;

import com.usermanager.model.DashboardModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl {

        private  String SELECT_SQL = "SELECT \n" +
                "    e.id AS emprunt_id,\n" +
                "    a.nom AS nom_adherent,\n" +
                "    e.document_type,\n" +
                "\n" +
                "    CASE \n" +
                "        WHEN e.document_type = 'LIVRE' THEN l.titre\n" +
                "        WHEN e.document_type = 'RAPPORT' THEN r.sujet\n" +
                "    END AS titre_document,\n" +
                "\n" +
                "    e.date_emprunt,\n" +
                "    e.date_retour_prevue,\n" +
                "    e.date_retour_effective,\n" +
                "\n" +
                "    CASE\n" +
                "        WHEN e.date_retour_effective IS NOT NULL THEN 'RETOURNE'\n" +
                "        WHEN e.date_retour_prevue < CURDATE() THEN 'EN_RETARD'\n" +
                "        ELSE 'EN_COURS'\n" +
                "    END AS statut\n" +
                "FROM emprunt e\n" +
                "JOIN adherants a \n" +
                "    ON e.adherent_id = a.id\n" +
                "LEFT JOIN LIVRE l \n" +
                "    ON e.document_type = 'LIVRE'\n" +
                "   AND e.document_id = l.id\n" +
                "LEFT JOIN rapport r \n" +
                "    ON e.document_type = 'RAPPORT'\n" +
                "   AND e.document_id = r.id\n" +
                "ORDER BY e.date_emprunt DESC;\n";


    // 🔹 TABLEAU HISTORIQUE
    public List<DashboardModel> getHistorique(String statut) throws SQLException {
        List<DashboardModel> list = new ArrayList<>();


        if (statut.equals("EN_COURS")) {
            SELECT_SQL += "WHERE e.date_retour_effective IS NULL";
        } else if (statut.equals("RETOURNE")) {
            SELECT_SQL += "WHERE e.date_retour_effective IS NOT NULL";
        }

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DashboardModel model = new DashboardModel();

                model.setId(rs.getInt("emprunt_id"));
                model.setNomAdherent(rs.getString("nom_adherent"));
                model.setType(rs.getString("document_type"));
                model.setTitre(rs.getString("titre_document"));
                model.setDateEmprunt(rs.getDate("date_emprunt").toLocalDate());

                Date retour = rs.getDate("date_retour_effective");
                if (retour != null) {
                    model.setDateRetour(retour.toLocalDate());
                }

                model.setStatut(rs.getString("statut"));

                list.add(model);
            }
        }
        return list;
    }

    // 🔹 KPI
    public int count(String table) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table;

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public int countEmpruntes(String type) throws SQLException {

        String sql =
                "SELECT COUNT(*) FROM emprunt " +
                        "WHERE document_type=? AND date_retour_effective IS NULL";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();

             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
