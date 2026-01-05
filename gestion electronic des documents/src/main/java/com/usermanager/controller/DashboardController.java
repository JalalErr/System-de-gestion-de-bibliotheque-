package com.usermanager.controller;

import com.usermanager.model.DashboardModel;
import com.usermanager.service.DashboardService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {

    @FXML private Label lblLivreTotal, lblLivreEmprunte, lblLivreRestant;
    @FXML private Label lblPfeTotal, lblPfeEmprunte, lblPfeRestant;

    @FXML private TableView<DashboardModel> table;
    @FXML private TableColumn<DashboardModel, String> colNom, colType, colTitre, colStatut;
    @FXML private TableColumn<DashboardModel, Integer> colId;

    private final DashboardService service = new DashboardService();

    public void initialize() {
        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());
        colNom.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNomAdherent()));
        colType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
        colTitre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTitre()));
        colStatut.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatut()));

        loadDashboard();
        loadTable("ALL");
    }

    private void loadDashboard() {
        try {
            int livres = service.getTotalLivres();
            int livresEmp = service.getLivresEmpruntes();

            System.out.println("le nombre totale des livre " + livres);
            System.out.println("le nombre totale des livre Emp " + livresEmp);


            lblLivreTotal.setText(String.valueOf(livres));
            lblLivreEmprunte.setText(String.valueOf(livresEmp));
            lblLivreRestant.setText(String.valueOf(livres - livresEmp));

            int pfe = service.getTotalRapports();
            int pfeEmp = service.getRapportsEmpruntes();

            System.out.println("le nombre totale des rrp" + pfe);
            System.out.println("le nombre totale des rrp Emp " + pfeEmp);

            lblPfeTotal.setText(String.valueOf(pfe));
            lblPfeEmprunte.setText(String.valueOf(pfeEmp));
            lblPfeRestant.setText(String.valueOf(pfe - pfeEmp));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showAll() { loadTable("ALL"); }

    @FXML
    private void showEnCours() { loadTable("EN_COURS"); }

    @FXML
    private void showRetourne() { loadTable("RETOURNE"); }

    private void loadTable(String filtre) {
        try {
            table.setItems(FXCollections.observableArrayList(service.getHistorique(filtre)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
