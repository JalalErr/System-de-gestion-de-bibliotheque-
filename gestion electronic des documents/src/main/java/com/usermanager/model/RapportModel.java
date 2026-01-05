package com.usermanager.model;

public class RapportModel {

    private int id;
    private String nomEtudiant;
    private String nomEncadrant;
    private String sujet;
    private String ecole;
    private boolean disponible;

    // Constructeurs
    public RapportModel() {}

    public RapportModel(String nomEtudiant, String nomEncadrant, String sujet, String ecole, boolean disponible ) {
        this.nomEtudiant = nomEtudiant;
        this.nomEncadrant = nomEncadrant;
        this.sujet = sujet;
        this.ecole = ecole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getnomEncadrant() {
        return nomEncadrant;
    }

    public void setnomEncadrant(String nomEncadrant) {
        this.nomEncadrant = nomEncadrant;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
