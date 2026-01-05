package com.usermanager.controller;

import com.usermanager.model.LivreModel;
import com.usermanager.model.RapportModel;
import com.usermanager.service.DocumentService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class DocumentController {

    private LivreModel liver = new LivreModel();
    private RapportModel rapport = new RapportModel();
    private DocumentService documentservice = new DocumentService();

    // Common Fields
    //@FXML private TextField searchField;
    @FXML private Button backButton;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private Button submitButton;

    // Livre Fields
    @FXML private VBox livreFormContainer;
    @FXML private TextField titreField;
    @FXML private TextField auteurField;
    @FXML private TextField isbnField;
    @FXML private CheckBox livreDisponibleCheck;

    // Rapport Fields
    @FXML private VBox rapportFormContainer;
    @FXML private TextField nomEtudiantField;
    @FXML private TextField nomEncadrantField;
    @FXML private TextField sujetField;
    @FXML private TextField ecoleField;
    @FXML private CheckBox rapportDisponibleCheck;

    @FXML
    public void initialize() {
        System.out.println("DocumentController initialized");

        // Configure ComboBox listener
        typeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Type sélectionné: " + newValue);
            handleTypeSelection(newValue);
        });

        // Configure back button
        if (backButton != null) {
            backButton.setOnAction(event -> handleBack());
        }

        // Configure submit button
        if (submitButton != null) {
            submitButton.setOnAction(event -> handleSubmit());
        }
    }

    private void handleTypeSelection(String type) {
        if (type == null || type.isEmpty()) {
            hideForms();
            return;
        }

        System.out.println("Affichage du formulaire pour: " + type);

        if (type.equals("Livre")) {
            showLivreForm();
        } else if (type.equals("Rapport")) {
            showRapportForm();
        }

        // Show submit button
        if (submitButton != null) {
            submitButton.setVisible(true);
            submitButton.setManaged(true);
        }
    }

    private void showLivreForm() {
        System.out.println("Affichage du formulaire Livre");

        // Show Livre form
        if (livreFormContainer != null) {
            livreFormContainer.setVisible(true);
            livreFormContainer.setManaged(true);
        }

        // Hide Rapport form
        if (rapportFormContainer != null) {
            rapportFormContainer.setVisible(false);
            rapportFormContainer.setManaged(false);
        }

        clearLivreFields();
    }

    private void showRapportForm() {
        System.out.println("Affichage du formulaire Rapport");

        // Show Rapport form
        if (rapportFormContainer != null) {
            rapportFormContainer.setVisible(true);
            rapportFormContainer.setManaged(true);
        }

        // Hide Livre form
        if (livreFormContainer != null) {
            livreFormContainer.setVisible(false);
            livreFormContainer.setManaged(false);
        }

        clearRapportFields();
    }

    private void hideForms() {
        System.out.println("Masquage de tous les formulaires");

        if (livreFormContainer != null) {
            livreFormContainer.setVisible(false);
            livreFormContainer.setManaged(false);
        }

        if (rapportFormContainer != null) {
            rapportFormContainer.setVisible(false);
            rapportFormContainer.setManaged(false);
        }

        if (submitButton != null) {
            submitButton.setVisible(false);
            submitButton.setManaged(false);
        }
    }

    private void handleSubmit() {

        String selectedType = typeComboBox.getValue();

        if (selectedType == null || selectedType.isEmpty()) {
            showAlert("Erreur", "Veuillez sélectionner un type de document.");
            return;
        }

        if (selectedType.equals("Livre")) {
            submitLivre();
        } else if (selectedType.equals("Rapport")) {
            submitRapport();
        }
    }

    private void submitLivre() {

        liver.setTitre(titreField.getText().trim());
        liver.setAuteur(auteurField.getText().trim());
        liver.setIsbn(isbnField.getText().trim());
        liver.setDisponible(livreDisponibleCheck.isSelected());

        // Create Livre object (you would save this to database)
        System.out.println("=== Livre ajouté ===");
        System.out.println("Titre: " + liver.getTitre());
        System.out.println("Auteur: " + liver.getAuteur());
        System.out.println("ISBN: " + liver.getIsbn());
        System.out.println("Disponible: " + liver.isDisponible());
        documentservice.addLivre(liver);
        showAlert("Succès", "Livre '" + liver.getTitre() + "' ajouté avec succès!");

        // Reset form
        clearLivreFields();
        typeComboBox.setValue(null);
        hideForms();
    }

    private void submitRapport() {
        rapport.setNomEtudiant(nomEtudiantField.getText().trim());
        rapport.setnomEncadrant(nomEncadrantField.getText().trim());
        rapport.setSujet (sujetField.getText().trim());
        rapport.setEcole(ecoleField.getText().trim());
        rapport.setDisponible (rapportDisponibleCheck.isSelected());

        // Create Rapport object (you would save this to database)
        System.out.println("=== Rapport ajouté ===");
        System.out.println("Nom Étudiant: " +  rapport.getNomEtudiant());
        System.out.println("Nom Encadrant: " + rapport.getnomEncadrant());
        System.out.println("Sujet: " + rapport.getSujet());
        System.out.println("École: " + rapport.getEcole());
        System.out.println("Disponible: " + rapport.getDisponible());
        documentservice.addRapport(rapport);
        showAlert("Succès", "Rapport '" + rapport.getSujet() + "' ajouté avec succès!");

        // Reset form
        clearRapportFields();
        typeComboBox.setValue(null);
        hideForms();
    }

    private void clearLivreFields() {
        if (titreField != null) titreField.clear();
        if (auteurField != null) auteurField.clear();
        if (isbnField != null) isbnField.clear();
        if (livreDisponibleCheck != null) livreDisponibleCheck.setSelected(true);
    }

    private void clearRapportFields() {
        if (nomEtudiantField != null) nomEtudiantField.clear();
        if (nomEncadrantField != null) nomEncadrantField.clear();
        if (sujetField != null) sujetField.clear();
        if (ecoleField != null) ecoleField.clear();
        if (rapportDisponibleCheck != null) rapportDisponibleCheck.setSelected(true);
    }

    private void handleBack() {
        // Navigate back to previous screen
        System.out.println("Retour à la page précédente");
        // You would implement actual navigation here
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}