package com.usermanager.dao.impl;

import com.usermanager.dao.UserHistoriqueDAO;
import com.usermanager.exception.DAOException;
import com.usermanager.model.UserHistoriqueModel;
import com.usermanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHistoriqueDAOImpl implements UserHistoriqueDAO {


    private static final String INSERT_SQL =
            "INSERT INTO historique (compte_id, action, description) VALUES (?, ?, ?)";

    private static final String SELECT_SQL =
            "SELECT * FROM historique WHERE compte_id = ? ORDER BY action_date DESC";

    @Override
    public void saveHistorique(UserHistoriqueModel history) {

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {

            pstmt.setInt(1, history.getUserId());
            pstmt.setString(2, history.getAction());
            pstmt.setString(3, history.getDescription());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error saving admin history", e);
        }
    }

    @Override
    public List<UserHistoriqueModel> findByAdmin(int adminId) {


        List<UserHistoriqueModel> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_SQL)) {

            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserHistoriqueModel h = new UserHistoriqueModel();
                //h.setId(rs.getInt("id"));
                h.setUserId(rs.getInt("compte_id"));
                h.setAction(rs.getString("action"));
                h.setDescription(rs.getString("description"));
                h.setDateAction(rs.getTimestamp("action_date").toLocalDateTime());

                list.add(h);
                for (UserHistoriqueModel l : list)  {
                    //System.out.println(l.getUserId() + l.getAction());
                }
                System.out.println("Liste des donnee" + h);
            }

        } catch (Exception e) {
            throw new DAOException("Error loading admin history", e);
        }

        return list;
    }

    @Override
    public List<UserHistoriqueModel> findAllHistorique() {
        return List.of();
    }


}
