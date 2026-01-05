package com.usermanager.controller;

import com.usermanager.model.SanctionModel;
import com.usermanager.service.SanctionService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SanctionController {

    @FXML
    private TableColumn<SanctionModel, String> colAdherent;


    @FXML
    private TableView<SanctionModel> tableSanctions;
    @FXML
    private TableColumn<SanctionModel, Integer> colId;
    @FXML
    private TableColumn<SanctionModel, Integer> colEmpruntId;
    @FXML
    private TableColumn<SanctionModel, String> colRaison;
    @FXML
    private TableColumn<SanctionModel, String> colDateSanction;
    @FXML
    private TableColumn<SanctionModel, Boolean> colActive;
    @FXML
    private TextField txtEmpruntId;
    @FXML
    private TextField txtRaison;
    @FXML
    private Label lblMessage;

    private final SanctionService sanctionService = new SanctionService();



    public void initialize() {


        colAdherent.setCellValueFactory(cellData -> {
            String fullName = cellData.getValue().getAdherentNom() + " " + cellData.getValue().getAdherentPrenom();
            return new SimpleStringProperty(fullName);
        });
        // Id de la sanction
        colId.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );

        // Id de l'emprunt lié
        colEmpruntId.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getEmpruntId()).asObject()
        );

        // Raison de la sanction
        colRaison.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRaison())
        );

        // Date de la sanction (formatée)
        colDateSanction.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDateSanction() != null) {
                // conversion LocalDateTime / Timestamp en LocalDate
                LocalDate date = cellData.getValue().getDateSanction();
                String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return new SimpleStringProperty(formattedDate);
            } else {
                return new SimpleStringProperty("");
            }
        });

        // Statut actif ou non
        colActive.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(cellData.getValue().isActive())
        );

        // Charger les sanctions depuis la base
        loadSanctions();
    }

    private void loadSanctions() {
        try {
            List<SanctionModel> sanctions = sanctionService.getAllSanctions();
            tableSanctions.setItems(FXCollections.observableArrayList(sanctions));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger les sanctions : " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleAddSanction() {
        try {
            int empruntId = Integer.parseInt(txtEmpruntId.getText());
            String raison = txtRaison.getText();

            if (raison.isEmpty()) {
                lblMessage.setText("La raison est obligatoire");
                return;
            }

            sanctionService.createSanction(empruntId, raison);

            lblMessage.setText("Sanction ajoutée avec succès");
            txtEmpruntId.clear();
            txtRaison.clear();

        } catch (NumberFormatException e) {
            lblMessage.setText("ID emprunt invalide");
        } catch (Exception e) {
            lblMessage.setText("Erreur : " + e.getMessage());
        }
    }
}
