package com.usermanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class loginController {

    public void goregistrationpage(ActionEvent event) throws IOException {

       switchScene.switchScene(event,"/views/registration.fxml", "Registeration Page");
    }
}
