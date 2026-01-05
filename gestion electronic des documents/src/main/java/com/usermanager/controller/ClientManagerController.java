package com.usermanager.controller;

import com.usermanager.dao.impl.ClientDAOImpl;
import com.usermanager.model.ClientModel;
import com.usermanager.service.ClientService;
import com.usermanager.service.UserHistoryService;
import com.usermanager.service.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ClientManagerController {
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
    @FXML
    private TextField lastNameField_;
    @FXML
    private TextField firstNameField_;


    ClientService clientservice = new ClientService();
    UserHistoryService userhistoriqueservice = new UserHistoryService();
    ClientDAOImpl clientdao = new ClientDAOImpl();
    UserSession usersession = new UserSession();

    @FXML
    public ClientModel createClient() {
        ClientModel user = new ClientModel();
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

        ClientModel user = createClient();
        System.out.println(
                user.getNom() + " | " +
                        user.getPrenom() + " | " +
                        user.getEmail() + " | " +
                        user.getAdress()
        );
        clientservice.create(user);

        userhistoriqueservice.log("Creer", "Creer un nouveau utlisateur");

        clearFrom();
    }

    @FXML
    public void deleteClient () {
        ClientModel user = new ClientModel();
        user.setNom(lastNameField_.getText());
        user.setCin(cinField_.getText());

        //clientservice.delete(user);
        //j'arrete ici
        if (clientservice.delete(user) != null) {
            userhistoriqueservice.log("Supprimer", "Supprimer un utilisateur" + user.getNom() + user.getPrenom()
                    + " Cin : " + user.getCin());
        }
        clearFrom();
    }

    @FXML
    public void clearFrom() {

        firstNameField.setText(null);
        lastNameField.setText(null);
        emailField.setText(null);
        phoneField.setText(null);
        addressField.setText(null);
        cinField.setText(null);
        lastNameField_.setText(null);
        cinField_.setText(null);
        firstNameField_.setText(null);


    }

}
