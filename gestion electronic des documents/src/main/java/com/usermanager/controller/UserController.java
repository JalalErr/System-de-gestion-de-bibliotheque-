package com.usermanager.controller;

import com.usermanager.model.UserModel;
import com.usermanager.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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
            //showStatus("✓ User created successfully", false);
        } catch (Exception e) {
            //showStatus("✗ Error: " + e.getMessage(), true);
        }
    }



}
