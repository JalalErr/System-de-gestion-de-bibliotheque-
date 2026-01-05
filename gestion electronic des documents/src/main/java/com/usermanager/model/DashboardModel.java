package com.usermanager.model;

import java.time.LocalDate;

public class DashboardModel {


    private int id;
    private String nomAdherent;
    private String type;
    private String titre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private String statut;

    public DashboardModel() {
    }

    public DashboardModel(int id, String nomAdherent, String type, String titre, LocalDate dateEmprunt, LocalDate dateRetour, String statut) {
        this.id = id;
        this.nomAdherent = nomAdherent;
        this.type = type;
        this.titre = titre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAdherent() {
        return nomAdherent;
    }

    public void setNomAdherent(String nomAdherent) {
        this.nomAdherent = nomAdherent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
