package com.usermanager.controller;


import com.usermanager.model.UserModel;
import com.usermanager.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserController {

        private final ObservableList<UserModel> userList;

        @FXML
        TextField nomField;
        @FXML
        TextField prenomField;
        @FXML
        TextField emailField;
        @FXML
        TextField passwordField;
        @FXML
        private TextField emailField_;
        @FXML
        private TextField passwordField_;
        @FXML
        private Label errorLabel;
        private UserService userService = new UserService();

        public UserController() {
            this.userService = new UserService();
            this.userList = FXCollections.observableArrayList();
        }


        private UserModel createUserFromFields() {
            UserModel user = new UserModel();
            user.setNom(nomField.getText().trim());
            user.setPrenom(prenomField.getText().trim());
            user.setEmail(emailField.getText().trim());
            user.setPassword(passwordField.getText().trim());
            System.out.println("l'utilisateur est bien creer");
            return user;
        }


        @FXML
        private void handleSave() {
            try {
                UserModel user = createUserFromFields();
                userService.createUser(user);
                //clearFields();
            } catch (Exception e) {
                //showStatus("✗ Error: " + e.getMessage(), true);
            }
        }

        @FXML
        private void handleLogin() {

            String email = emailField_.getText();
            String password = passwordField_.getText();

            System.out.println("email: " + email);
            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs");
                return;
            }
                //userService.login(email, password);
            boolean authenticate = userService.login(email, password);

            if (userService.login(email, password)) {
                // Redirection vers la page principale
                SceneManager.switchScene("dashboard.fxml");

            } else {
                errorLabel.setText("Email ou mot de passe incorrect");
            }
        }

        public void goregistrationpage(ActionEvent event) throws IOException {

            switchScene.switchScene(event,"/views/registration.fxml", "Registeration Page");
        }

        public void gologinpage(ActionEvent event) throws IOException {
            switchScene.switchScene(event, "/views/login.fxml", "Login");
        }

}