package com.usermanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SanctionModel {
    private int id;
    private int empruntId;
    private String raison;
    private LocalDate dateSanction;
    private LocalDate dateFin;
    private boolean active;
    private String adherentNom;
    private String adherentPrenom;

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmpruntId() { return empruntId; }
    public void setEmpruntId(int empruntId) { this.empruntId = empruntId; }

    public String getRaison() { return raison; }
    public void setRaison(String raison) { this.raison = raison; }

    public LocalDate getDateSanction() { return dateSanction; }
    public void setDateSanction(LocalDate dateSanction) { this.dateSanction = dateSanction; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getAdherentNom() { return adherentNom; }
    public void setAdherentNom(String adherentNom) { this.adherentNom = adherentNom; }

    public String getAdherentPrenom() { return adherentPrenom; }
    public void setAdherentPrenom(String adherentPrenom) { this.adherentPrenom = adherentPrenom; }

}
