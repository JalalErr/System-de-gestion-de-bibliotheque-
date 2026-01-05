package com.usermanager.service;

import com.usermanager.dao.impl.EmpruntDAO;
import com.usermanager.model.EmpruntModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmpruntService {

    private  UserHistoryService userhistoriqueservice = new UserHistoryService();

    private EmpruntDAO empruntDAO = new EmpruntDAO();
    //private SanctionDAO sanctionDAO = new SanctionDAO();

    //  Emprunter un document
    public void emprunter(EmpruntModel emprunt) throws SQLException {

        if (emprunt.getDateRetourPrevue().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date de retour invalide");
        }
        userhistoriqueservice.log("Emprunter", "Emprunter un document ");

        empruntDAO.save(emprunt);
    }

    //  Retour + sanction automatique
    public void retournerDocument(EmpruntModel emprunt) throws SQLException {

        empruntDAO.retourner(emprunt.getId());

        if (emprunt.isEnRetard()) {
            //sanctionDAO.creerSanction(
                    //e.getId(),
                //    "Retard de retour du document"
           // );
        }
    }

    public List<EmpruntModel> lister() throws SQLException {
        return empruntDAO.findAll();
    }

    public List<EmpruntModel> obtenirEmpruntsEnCours() {
        try {
            return empruntDAO.findAll();
        } catch (SQLException e) {
            System.err.println("Erreur: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
