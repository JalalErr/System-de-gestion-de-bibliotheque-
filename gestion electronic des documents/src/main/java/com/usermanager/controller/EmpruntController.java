package com.usermanager.controller;

import com.usermanager.model.DocumentType;
import com.usermanager.model.EmpruntModel;
import com.usermanager.service.EmpruntService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.time.LocalDate;


public class EmpruntController {


    @FXML private TextField adherentIdField;
    @FXML private TextField documentIdField;
    @FXML private ComboBox<DocumentType> typeCombo;
    @FXML private DatePicker retourPicker;

    private EmpruntService service = new EmpruntService();

    @FXML
    public void initialize() {
        typeCombo.getItems().addAll(DocumentType.values());
    }

    @FXML
    public void emprunter() throws SQLException {
        EmpruntModel emprunt = new EmpruntModel();
//        emprunt.setAdherantcin(adherentIdField.getText());
        emprunt.setAdherantCin(adherentIdField.getText());
        emprunt.setDocumentId(Integer.parseInt(documentIdField.getText()));
        emprunt.setType(typeCombo.getValue());
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(retourPicker.getValue());

        service.emprunter(emprunt);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Emprunt enregistré");
        alert.show();
    }
}
