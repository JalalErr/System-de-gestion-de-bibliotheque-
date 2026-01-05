package com.usermanager.service;

import com.usermanager.dao.SanctionDAO;
import com.usermanager.dao.impl.EmpruntDAO;
import com.usermanager.dao.impl.SanctionDAOImpl;
import com.usermanager.model.EmpruntModel;
import com.usermanager.model.SanctionModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import java.util.List;

public class SanctionService {

    private final SanctionDAO sanctionDAO = new SanctionDAOImpl();
    private final EmpruntDAO empruntDAO = new EmpruntDAO();

    public void ajouterSanctionsAutomatiques() throws SQLException {
        List<EmpruntModel> emprunts = empruntDAO.findAll();
        LocalDate today = LocalDate.now();

        for (EmpruntModel emprunt : emprunts) {
            if (emprunt.getDateRetourPrevue().isBefore(today) && emprunt.getDateRetourEffective() == null) {
                if (!sanctionDAO.existsByEmpruntId(emprunt.getId())) {
                    SanctionModel sanction = new SanctionModel();
                    sanction.setEmpruntId(emprunt.getId());
                    sanction.setRaison("Retard de retour du document");
                    sanction.setActive(true);
                    sanctionDAO.save(sanction);
                }
            }
        }
    }

    public List<SanctionModel> getAllSanctions() {
        return sanctionDAO.findAll();
    }


    public void createSanction(int empruntId, String raison) {

        SanctionModel sanction = new SanctionModel();
        sanction.setEmpruntId(empruntId);
        sanction.setRaison(raison);
        sanction.setDateSanction(LocalDate.now());
        sanction.setActive(true);

        sanctionDAO.addSanction(sanction);
    }
}
