package com.usermanager.controller;

import com.usermanager.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField_;

    @FXML
    private Label errorLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        System.out.println("From handel save...");

        String email = emailField.getText();
        String password = passwordField_.getText();
        System.out.println("email: " + email);
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        boolean authenticated = userService.login(email, password);

        if (authenticated) {
            // Redirection vers la page principale
            SceneManager.switchScene("dashboard.fxml");

        } else {
            errorLabel.setText("Email ou mot de passe incorrect");
        }
    }

}
