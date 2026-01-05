package com.usermanager.controller;

import com.usermanager.dao.impl.UserDAOImpl;
import com.usermanager.model.UserModel;
import com.usermanager.service.UserHistoryService;
import com.usermanager.service.UserService;
import com.usermanager.service.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;

public class UserController {

    @FXML
    private TextField emailField_;
    @FXML
    private TextField passwordField_;
    @FXML
    private Label errorLabel;
    @FXML
    private Label nomlabel_;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameEditField;
    @FXML
    private TextField lastNameEditField;
    @FXML
    private  TextField emailEditField;

    private final ObservableList<Object> userList = FXCollections.observableArrayList();
    private UserService userService = new UserService();
    private  UserHistoryService userhistoriqueservice = new UserHistoryService();
    UserSession usersession = new UserSession();

    private UserModel createUserFromFields() {
        UserModel user = new UserModel();
        user.setNom(nomField.getText().trim());
        user.setPrenom(prenomField.getText().trim());
        user.setEmail(emailField.getText().trim());
        user.setPassword(passwordField.getText().trim());
        System.out.println("Utilisateur créé : " + user.getNom());
        return user;
    }

    @FXML
    private void handleSave() {
        try {
            UserModel user = createUserFromFields();
            userService.createUser(user);
            System.out.println("Utilisateur sauvegardé !");
        } catch (Exception e) {
            System.err.println("Erreur création utilisateur : " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField_.getText().trim();
        String password = passwordField_.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        Optional<UserModel> userOpt = userService.login(email, password);

        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            UserSession.login(user);
            System.out.println("Session créée pour : " + user.getNom());

            System.out.println("Session créée pour id : " + user.getId());
            System.out.println("Redirection ---> ");
            userhistoriqueservice.log("LOGIN", "Connexion admin");
            //switchScene.switchScene(event, "/views/profile/ProfileAdmin.fxml", "Dashboard");
            SceneManager.switchScene("dashboard/dashboard.fxml", "Dashboard");

        } else {
            errorLabel.setText("Email ou mot de passe incorrect");
        }
    }

    @FXML
    public void initialize() {
        UserModel user = UserSession.getUser();
        if (user != null && nomlabel_ != null) {
            nomlabel_.setText(user.getNom());
        }
    }

    @FXML
    public void editUser() {
        UserModel user = new UserModel();
        user = UserSession.getUser();
        System.out.println("user : " + user);
        user.setNom(firstNameEditField.getText());
        user.setPrenom(lastNameEditField.getText());
        user.setEmail(emailEditField.getText());
        System.out.println("user in edite user: " + user);

        System.out.println("Nouveau nom : " + user.getNom() + " \n " + "Nouveau Prenom : " + user.getPrenom());

        //userService.updateUser(user);
        if (userService.updateUser(user) != null) {

            userhistoriqueservice.log("Edite", "Editer les infos de l'admin");
            clearFrom();
        }
    }

    public void clearFrom() {

        firstNameEditField.setText(null);
        lastNameEditField.setText(null);
        emailEditField.setText(null);

    }
    @FXML
    public void goregistrationpage(ActionEvent event) throws IOException {
        switchScene.switchScene(event, "/views/registration.fxml", "Inscription");
    }

    @FXML
    public void gologinpage(ActionEvent event) throws IOException {
        switchScene.switchScene(event, "/views/auth/LoginPage.fxml", "Login");
    }
}

