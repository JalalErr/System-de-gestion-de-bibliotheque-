package com.usermanager.controller;

import com.usermanager.model.UserModel;
import com.usermanager.service.UserServices;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;

public class AddUserController {
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cinField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cinField_;

    UserServices userservices = new UserServices();
     @FXML
    public UserModel createUser() {
        UserModel user = new UserModel();
        user.setNom(lastNameField.getText());
        user.setPrenom(firstNameField.getText());
        user.setEmail(emailField.getText());
        user.setNumero(phoneField.getText());
        user.setCin(cinField.getText());
        user.setAdress(addressField.getText());
         System.out.println(
                 user.getNom() + " | " +
                         user.getPrenom() + " | " +
                         user.getEmail() + " | " +
                         user.getNumero() + " | " +
                         user.getAdress()
         );
        return user;
    }

    @FXML
    public void handelSave() {

        UserModel user = createUser();
        System.out.println(
                user.getNom() + " | " +
                        user.getPrenom() + " | " +
                        user.getEmail() + " | " +
                        user.getAdress()
        );
        userservices.create(user);
        ClearFrom();
    }

    @FXML
    public void deletAdherant () {
         UserModel user = new UserModel();
         user.setCin(cinField_.getText());
         userservices.delete(user);
    }
    @FXML
    public void ClearFrom() {

        firstNameField.setText(null);
        lastNameField.setText(null);
        emailField.setText(null);
        phoneField.setText(null);
        addressField.setText(null);

    }

}
