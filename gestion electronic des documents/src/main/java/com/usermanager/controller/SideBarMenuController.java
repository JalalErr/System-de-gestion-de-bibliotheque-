package com.usermanager.controller;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.usermanager.model.UserModel;
import com.usermanager.service.UserSession;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SideBarMenuController {

    @FXML
    public void gotodashboard() {
        SceneManager.switchScene("dashboard/dashboard.fxml", "Dashboard");
    }

    @FXML
    public void goToAddUser() {
        SceneManager.switchScene("ClientManager/gestionClient.fxml", "Gestion des Clients");
    }

    @FXML
    public void goToProfile() {
        SceneManager.switchScene("profile/ProfileAdmin.fxml", "Profile");
    }

    @FXML
    public void logout() {
        SceneManager.switchScene("auth/LoginPage.fxml", "Sing In");
    }

    @FXML
    public void goToHistory() {
        SceneManager.switchScene("history/Historique.fxml" , "Historique");
    }

    @FXML
    public void goToAddDocument() {
        SceneManager.switchScene("Emprunt/AjouterDocument.fxml" , "Ajouter Document");
    }

    @FXML
    public void goToEmprunt() {
        SceneManager.switchScene("Emprunt/Emprunt.fxml", "Emprunter");
    }

    @FXML
    public void goToSanction() {
        SceneManager.switchScene("Emprunt/SanctionPage.fxml", "Sanction");
    }
}
