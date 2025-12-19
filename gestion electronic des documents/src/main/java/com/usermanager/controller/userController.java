package com.usermanager.controller;


import com.usermanager.dao.impl.UserDAOImpl;
import com.usermanager.model.UserModel;
import com.usermanager.model.UserRole;
import com.usermanager.service.UserService;
import com.usermanager.service.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserController {

    private final ObservableList<Object> userList;
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
    @FXML
    private Label nomlabel_;

    private UserService userService = new UserService();
    private UserDAOImpl userdaoimpl = new UserDAOImpl();


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

        Optional<UserModel> userOpt = userService.login(email, password);

        if (userOpt.isPresent()) {
            UserModel user = userOpt.get();
            UserSession.login(user);
            System.out.println("Creation de session " + user.getNom());

            if (user.getRole() == UserRole.ADMIN) {
                System.out.println("Redirection --->");
                //SceneManager.switchScene("profile/ProfileAdmin.fxml", "ProfileUser");
                SceneManager.switchScene("dashboard/UserDashboard.fxml", "ProfileUser");

            } else {
                System.out.println("Redirection User --->");
                //SceneManager.switchScene("profile/ProfileUser.fxml", "ProfileUser");
                SceneManager.switchScene("dashboard/UserDashboard.fxml", "ProfileUser");
            }
        } else{
            errorLabel.setText("Email ou mot de passe incorrect");
        }

    }
    @FXML
    public void initialize() {
        UserModel user = UserSession.getUser();
        if (user != null) {
            System.out.println(user.getNom());
            nomlabel_.setText(user.getNom());
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        UserModel user = UserSession.getUser();
        UserSession.logout(user);
        SceneManager.switchScene("LoginPage.fxml", "Login");
    }

    public void goregistrationpage(ActionEvent event) throws IOException {

        //switchScene.switchScene(event,"/views/registration.fxml", "Registeration Page");
        //switchScene.switchScene(event,"/views/registration.fxml", "Sign Up Page");
        //SceneManager.switchScene("registration.fxml", "Sign In");
        switchScene.switchScene(event, "/views/registration.fxml", "Login");

    }

    public void gologinpage(ActionEvent event) throws IOException {
        switchScene.switchScene(event, "/views/LoginPage.fxml", "Login");
    }

}