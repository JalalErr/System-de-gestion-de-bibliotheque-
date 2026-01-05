package com.usermanager.model;
import java.time.LocalDate;


public class EmpruntModel {

    private int id;
    private int adherant_id;
    private String adherantCin;
    private DocumentType type;
    private int documentId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    public EmpruntModel() {}

    public EmpruntModel(int adherentId,
                   DocumentType documentType,
                   int documentId,
                   LocalDate dateEmprunt,
                   LocalDate dateRetourPrevue, String adherantCin) {

        this.adherant_id = adherentId;
        this.type = documentType;
        this.documentId = documentId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.adherantCin = adherantCin;
    }

    /**  Emprunt encore en cours ? */
    public boolean isEnCours() {
        return dateRetourEffective == null;
    }

    /**  Emprunt en retard ? */
    public boolean isEnRetard() {
        return isEnCours() && LocalDate.now().isAfter(dateRetourPrevue);
    }

    /**  Nombre de jours de retard */
    public long getJoursDeRetard() {
        if (!isEnRetard()) return 0;
        return java.time.temporal.ChronoUnit.DAYS
                .between(dateRetourPrevue, LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdherant_id() {
        return adherant_id;
    }

    public void setAdherant_id(int adherant_id) {
        this.adherant_id = adherant_id;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public String getAdherantCin() {
        return adherantCin;
    }

    public void setAdherantCin(String adherantCin) {
        this.adherantCin = adherantCin;
    }
}
