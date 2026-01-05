package com.usermanager.controller;

import com.usermanager.model.UserHistoriqueModel;
import com.usermanager.service.UserHistoryService;
import com.usermanager.service.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.util.List;

public class UserHistoriqueController {

    UserSession usersession = new UserSession();

    @FXML
    private TableView<UserHistoriqueModel> historiqueTable;

    @FXML
    private TableColumn<UserHistoriqueModel, Integer> idColumn;

    @FXML
    private TableColumn<UserHistoriqueModel, String> actionColumn;

    @FXML
    private TableColumn<UserHistoriqueModel, String> descriptionColumn;

    @FXML
    private TableColumn<UserHistoriqueModel, LocalDateTime> dateColumn;

    private final UserHistoryService historyService = new UserHistoryService();

    @FXML
    public void initialize() {
        // Lier les colonnes aux attributs du modèle
        idColumn.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAction"));

        loadHistorique();
    }

    private void loadHistorique() {

        int adminId = UserSession.getUser().getId();
        System.out.println("Id Admin : " + adminId);

        List<UserHistoriqueModel> historyList =
                historyService.getHistoriqueByAdmin(adminId);

        ObservableList<UserHistoriqueModel> data = FXCollections.observableArrayList(historyList);

        historiqueTable.setItems(data);
    }
}
