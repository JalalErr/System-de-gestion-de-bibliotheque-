package com.usermanager.service;

import com.usermanager.dao.impl.LivreDAOImpl;
import com.usermanager.dao.impl.RapportDAOImpl;
import com.usermanager.model.LivreModel;
import com.usermanager.model.RapportModel;

import java.sql.Connection;
import java.time.Period;

public class DocumentService {

    private  UserHistoryService userhistoriqueservice = new UserHistoryService();

    private final LivreDAOImpl livreDAO = new LivreDAOImpl();
    private final LivreModel livre = new LivreModel();
    private final RapportDAOImpl rapportDAO = new RapportDAOImpl();
    private final RapportModel rapport = new RapportModel();

    public void addLivre(LivreModel livre){

        if (livre.getTitre().isEmpty() || livre.getAuteur().isEmpty() || livre.getIsbn().isEmpty()) {
            System.out.println("Erreur, Veuillez remplir tous les champs obligatoires.");
            return;
        }

        userhistoriqueservice.log("Ajouter", "Ajouter un document de type Livre " );

            livreDAO.saveBook(livre);
    }

    public void addRapport(RapportModel rapport) {

        if (rapport.getNomEtudiant().isEmpty() || rapport.getnomEncadrant().isEmpty() || rapport.getEcole().isEmpty()) {
            System.out.println("Erreur, Veuillez remplir tous les champs obligatoires.");
            return;
        }

        userhistoriqueservice.log("Ajouter", "Ajouter un document de type Rapport " );

        rapportDAO.saveRapport(rapport);
    }
}
